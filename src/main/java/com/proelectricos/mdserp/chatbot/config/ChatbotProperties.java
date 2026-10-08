package com.proelectricos.mdserp.chatbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * Configuración del chatbot (prefijo "chatbot" en application.properties).
 * Los secretos se leen de variables de entorno o del archivo .env (ver application.properties).
 */
@ConfigurationProperties(prefix = "chatbot")
public record ChatbotProperties(
        boolean enabled,
        Gemini gemini,
        Telegram telegram,
        Database database,
        Tts tts) {

    /**
     * @param maxToolCalls rondas máximas de llamadas a herramientas por mensaje del usuario
     */
    public record Gemini(String apiKey, String model, int maxToolCalls) {
    }

    /**
     * @param allowedChatIds chats de Telegram autorizados; si está vacío nadie puede usar el bot
     */
    public record Telegram(String token, Set<Long> allowedChatIds) {

        public boolean autorizado(long chatId) {
            return allowedChatIds != null && allowedChatIds.contains(chatId);
        }
    }

    /**
     * Conexión exclusiva del chatbot, apuntando a ErpDb.
     * Lo ideal es un usuario SQL con solo db_datareader sobre ErpDb.
     */
    public record Database(String url, String username, String password, int maxRows, int queryTimeoutSeconds) {
    }

    /**
     * Respuestas habladas con Gemini TTS.
     *
     * @param mode          cuándo responder también con nota de voz
     * @param maxCharacters texto máximo a leer; lo demás queda solo en el mensaje escrito
     */
    public record Tts(Mode mode, String model, String voice, String style, int maxCharacters, int timeoutSeconds) {

        public enum Mode {
            /** Nunca envía audio */
            NEVER,
            /** Solo cuando el usuario escribió con una nota de voz */
            ON_VOICE,
            /** Siempre acompaña la respuesta con audio */
            ALWAYS
        }

        public boolean aplica(boolean usuarioEnvioAudio) {
            return mode == Mode.ALWAYS || (mode == Mode.ON_VOICE && usuarioEnvioAudio);
        }
    }
}
