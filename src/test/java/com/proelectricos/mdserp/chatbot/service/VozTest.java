package com.proelectricos.mdserp.chatbot.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.assertj.core.api.Assertions.assertThat;

class VozTest {

    @Test
    void quitaTablasMarkdownYEmojis() {
        String texto = "📊 **Pedido 49594**\n| Item | Código |\n|---|---|\n| 1 | ABC |\n\n\n- Total: `1.000` ✨";

        assertThat(TextoParaVoz.preparar(texto, 1500)).isEqualTo("Pedido 49594\n- Total: 1.000");
    }

    @Test
    void recortaTextosLargosEnUnEspacio() {
        assertThat(TextoParaVoz.preparar("uno dos tres cuatro", 10))
                .isEqualTo("uno dos. El resto lo tienes en el mensaje de texto.");
    }

    @Test
    void codificaPcmDeGeminiAMp3() throws IOException {
        // 1 segundo de tono de 440 Hz, PCM 16 bits mono a 24 kHz como lo entrega Gemini TTS
        ByteBuffer pcm = ByteBuffer.allocate(24000 * 2).order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < 24000; i++) {
            pcm.putShort((short) (Math.sin(2 * Math.PI * 440 * i / 24000.0) * 8000));
        }

        byte[] mp3 = Mp3Encoder.desdeAudioGemini(pcm.array(), "audio/L16;codec=pcm;rate=24000");

        // Encabezado de trama MPEG audio: 11 bits de sincronía en 1
        assertThat(mp3.length).isGreaterThan(1000);
        assertThat(mp3[0] & 0xFF).isEqualTo(0xFF);
        assertThat(mp3[1] & 0xE0).isEqualTo(0xE0);
    }
}
