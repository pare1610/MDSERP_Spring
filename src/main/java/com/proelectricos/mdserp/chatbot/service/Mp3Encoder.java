package com.proelectricos.mdserp.chatbot.service;

import de.sciss.jump3r.lowlevel.LameEncoder;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Convierte el audio de Gemini TTS (WAV, o PCM crudo "audio/L16;rate=24000") a MP3,
 * uno de los formatos que Telegram acepta para notas de voz.
 */
final class Mp3Encoder {

    private static final int BITRATE_KBPS = 48;
    private static final Pattern TASA_MUESTREO = Pattern.compile("rate=(\\d+)");

    private Mp3Encoder() {
    }

    static byte[] desdeAudioGemini(byte[] audio, String mimeType) throws IOException {
        AudioFormat formato;
        byte[] pcm;
        if (esWav(audio)) {
            try (AudioInputStream wav = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audio))) {
                formato = wav.getFormat();
                pcm = wav.readAllBytes();
            } catch (UnsupportedAudioFileException e) {
                throw new IOException("Formato WAV no soportado: " + mimeType, e);
            }
        } else {
            // PCM 16 bits mono little-endian; la tasa viene en el mime type
            Matcher tasa = TASA_MUESTREO.matcher(mimeType == null ? "" : mimeType);
            float muestreo = tasa.find() ? Float.parseFloat(tasa.group(1)) : 24000f;
            formato = new AudioFormat(muestreo, 16, 1, true, false);
            pcm = audio;
        }
        return codificar(pcm, formato);
    }

    private static byte[] codificar(byte[] pcm, AudioFormat formato) {
        LameEncoder encoder = new LameEncoder(formato, BITRATE_KBPS,
                LameEncoder.CHANNEL_MODE_MONO, LameEncoder.QUALITY_MIDDLE, false);
        try {
            ByteArrayOutputStream mp3 = new ByteArrayOutputStream();
            byte[] salida = new byte[encoder.getMP3BufferSize()];
            int bloque = encoder.getPCMBufferSize();
            for (int posicion = 0; posicion < pcm.length; posicion += bloque) {
                int escritos = encoder.encodeBuffer(pcm, posicion, Math.min(bloque, pcm.length - posicion), salida);
                mp3.write(salida, 0, escritos);
            }
            mp3.write(salida, 0, encoder.encodeFinish(salida));
            return mp3.toByteArray();
        } finally {
            encoder.close();
        }
    }

    private static boolean esWav(byte[] audio) {
        return audio.length > 12 && audio[0] == 'R' && audio[1] == 'I' && audio[2] == 'F' && audio[3] == 'F';
    }
}
