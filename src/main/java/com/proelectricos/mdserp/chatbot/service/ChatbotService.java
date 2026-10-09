package com.proelectricos.mdserp.chatbot.service;

import com.google.genai.Chat;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.FunctionCall;
import com.google.genai.types.FunctionResponse;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.google.genai.types.ThinkingConfig;
import com.proelectricos.mdserp.chatbot.config.ChatbotProperties;
import com.proelectricos.mdserp.chatbot.erpdb.ErpDbReadOnlyQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Conversación con Gemini: un historial independiente por chat y el ciclo de llamadas a herramientas
 * (el modelo pide una función, se ejecuta contra ErpDb y se le devuelve el resultado hasta que responde en texto).
 */
@Slf4j
@Service
@ConditionalOnProperty(prefix = "chatbot", name = "enabled", havingValue = "true")
public class ChatbotService {

    private static final String SIN_RESPUESTA = "No obtuve respuesta.";

    private final Client gemini;
    private final ErpDbChatTools tools;
    private final ErpDbReadOnlyQueries queries;
    private final ChatbotProperties.Gemini config;
    // Instrucciones de comportamiento + conocimiento del negocio (archivos editables en resources/chatbot)
    private final String instrucciones;
    private final Map<Long, Conversacion> chats = new ConcurrentHashMap<>();
    // Esquema de ErpDb leído una sola vez de la base; null hasta que se lea con éxito
    private volatile String esquema;

    public ChatbotService(Client gemini, ErpDbChatTools tools, ErpDbReadOnlyQueries queries, ChatbotProperties properties) {
        this.gemini = gemini;
        this.tools = tools;
        this.queries = queries;
        this.config = properties.gemini();
        this.instrucciones = leer("chatbot/instrucciones.md") + "\n\n" + leer("chatbot/conocimiento.md");
    }

    /** Envía un texto del usuario y devuelve la respuesta final del modelo. */
    public String responder(long chatId, String texto) {
        return responder(chatId, List.of(Part.fromText(texto)));
    }

    /** Envía partes arbitrarias (texto, audio...) y devuelve la respuesta final del modelo. */
    public String responder(long chatId, List<Part> partes) {
        // Tras un rato sin mensajes se empieza de cero: un historial corto hace cada llamada más rápida
        Conversacion conversacion = chats.compute(chatId, (id, actual) ->
                actual == null || actual.inactiva(config.minutosInactividad()) ? new Conversacion(crearChat()) : actual);
        Chat chat = conversacion.chat;
        // El historial de un Chat no es seguro entre hilos: un mensaje a la vez por conversación
        synchronized (chat) {
            conversacion.ultimoUso = Instant.now();
            try {
                GenerateContentResponse respuesta = chat.sendMessage(Content.builder().role("user").parts(partes).build());
                for (int ronda = 0; !respuesta.functionCalls().isEmpty(); ronda++) {
                    if (ronda >= config.maxToolCalls()) {
                        chats.remove(chatId, conversacion);
                        return "No pude completar la consulta con un número razonable de pasos. "
                                + "Intenta una pregunta más concreta.";
                    }
                    respuesta = chat.sendMessage(Content.builder()
                            .role("user")
                            .parts(respuestasDeFunciones(respuesta.functionCalls()))
                            .build());
                }
                String texto = respuesta.text();
                return texto == null || texto.isBlank() ? SIN_RESPUESTA : texto;
            } catch (RuntimeException e) {
                // Un historial con una llamada a función sin respuesta deja inservible la conversación
                chats.remove(chatId, conversacion);
                throw e;
            }
        }
    }

    public void reiniciar(long chatId) {
        chats.remove(chatId);
    }

    private List<Part> respuestasDeFunciones(List<FunctionCall> llamadas) {
        return llamadas.stream()
                .map(llamada -> {
                    FunctionResponse.Builder respuesta = FunctionResponse.builder()
                            .name(llamada.name().orElse(""))
                            .response(tools.ejecutar(llamada));
                    llamada.id().ifPresent(respuesta::id);
                    return Part.builder().functionResponse(respuesta.build()).build();
                })
                .toList();
    }

    private Chat crearChat() {
        String sistema = instrucciones
                + "\n\nEsquema completo de ErpDb (ya lo conoces, no consultes INFORMATION_SCHEMA):\n" + esquema()
                + "\nFecha actual: " + LocalDate.now() + ".";
        GenerateContentConfig.Builder configuracion = GenerateContentConfig.builder()
                .systemInstruction(Content.fromParts(Part.fromText(sistema)))
                .tools(tools.tool());
        if (config.thinkingLevel() != null && !config.thinkingLevel().isBlank()) {
            configuracion.thinkingConfig(ThinkingConfig.builder().thinkingLevel(config.thinkingLevel().trim()).build());
        }
        return gemini.chats.create(config.model(), configuracion.build());
    }

    // Si ErpDb no responde, el bot sigue funcionando (puede explorar con consultar_sql) y se reintenta en el próximo chat
    private String esquema() {
        if (esquema == null) {
            try {
                esquema = queries.describirEsquema();
            } catch (SQLException | RuntimeException e) {
                log.warn("Chatbot: no se pudo leer el esquema de ErpDb: {}", e.getMessage());
                return "(no disponible; consulta INFORMATION_SCHEMA.COLUMNS solo si lo necesitas)\n";
            }
        }
        return esquema;
    }

    private static String leer(String recurso) {
        try {
            return new ClassPathResource(recurso).getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer " + recurso, e);
        }
    }

    private static final class Conversacion {
        private final Chat chat;
        private volatile Instant ultimoUso = Instant.now();

        private Conversacion(Chat chat) {
            this.chat = chat;
        }

        private boolean inactiva(int minutos) {
            return minutos > 0 && ultimoUso.isBefore(Instant.now().minus(Duration.ofMinutes(minutos)));
        }
    }
}
