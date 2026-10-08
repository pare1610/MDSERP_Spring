package com.proelectricos.mdserp.chatbot.service;

import com.google.genai.Client;
import com.google.genai.types.Blob;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpRetryOptions;
import com.google.genai.types.Part;
import com.google.genai.types.PrebuiltVoiceConfig;
import com.google.genai.types.SpeechConfig;
import com.google.genai.types.VoiceConfig;
import com.proelectricos.mdserp.chatbot.config.ChatbotProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Respuestas habladas: convierte el texto de la respuesta en una nota de voz MP3 con Gemini TTS.
 */
@Service
@ConditionalOnProperty(prefix = "chatbot", name = "enabled", havingValue = "true")
public class VozService {

    private final Client gemini;
    private final ChatbotProperties.Tts config;
    private final GenerateContentConfig configuracionVoz;

    public VozService(Client gemini, ChatbotProperties properties) {
        this.gemini = gemini;
        this.config = properties.tts();
        this.configuracionVoz = GenerateContentConfig.builder()
                // El audio es complementario: no vale la pena esperar tanto como por la respuesta escrita
                .httpOptions(HttpOptions.builder()
                        .timeout(config.timeoutSeconds() * 1000)
                        .retryOptions(HttpRetryOptions.builder().attempts(2).initialDelay(2.0)))
                .responseModalities("AUDIO")
                .speechConfig(SpeechConfig.builder()
                        .voiceConfig(VoiceConfig.builder()
                                .prebuiltVoiceConfig(PrebuiltVoiceConfig.builder().voiceName(config.voice()).build())
                                .build())
                        .build())
                .build();
    }

    public boolean aplica(boolean usuarioEnvioAudio) {
        return config.aplica(usuarioEnvioAudio);
    }

    /** @return el audio MP3, o vacío si no queda nada que leer (p. ej. la respuesta era solo una tabla) */
    public Optional<byte[]> sintetizarMp3(String respuesta) throws IOException {
        String hablado = TextoParaVoz.preparar(respuesta, config.maxCharacters());
        if (hablado.isBlank()) {
            return Optional.empty();
        }
        GenerateContentResponse audio = gemini.models.generateContent(
                config.model(), config.style() + "\n" + hablado, configuracionVoz);
        Blob datos = audio.candidates().orElse(List.of()).stream()
                .flatMap(candidato -> candidato.content().flatMap(c -> c.parts()).orElse(List.of()).stream())
                .map(Part::inlineData)
                .flatMap(Optional::stream)
                .filter(blob -> blob.data().isPresent())
                .findFirst()
                .orElseThrow(() -> new IOException("Gemini TTS no devolvió audio"));
        return Optional.of(Mp3Encoder.desdeAudioGemini(datos.data().get(), datos.mimeType().orElse("")));
    }
}
