package com.proelectricos.mdserp.chatbot.service;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Prepara la respuesta escrita para leerla en voz alta: quita tablas, bloques de código, Markdown y emojis.
 */
final class TextoParaVoz {

    private static final String AVISO_CORTE = ". El resto lo tienes en el mensaje de texto.";

    private static final Pattern BLOQUE_CODIGO = Pattern.compile("```.*?```", Pattern.DOTALL);
    private static final Pattern MARKDOWN = Pattern.compile("[*_`#>~]");
    private static final Pattern EMOJIS = Pattern.compile("[\\x{1F000}-\\x{1FAFF}\\x{2600}-\\x{27BF}\\x{FE0F}\\x{200D}]");
    private static final Pattern LINEAS_VACIAS = Pattern.compile("\\n{2,}");

    private TextoParaVoz() {
    }

    static String preparar(String texto, int maximo) {
        String sinTablas = Arrays.stream(texto.split("\n"))
                .filter(linea -> !linea.strip().startsWith("|"))
                .collect(Collectors.joining("\n"));
        String limpio = BLOQUE_CODIGO.matcher(sinTablas).replaceAll("");
        limpio = MARKDOWN.matcher(limpio).replaceAll("");
        limpio = EMOJIS.matcher(limpio).replaceAll("");
        limpio = LINEAS_VACIAS.matcher(limpio).replaceAll("\n").strip();
        if (limpio.length() <= maximo) {
            return limpio;
        }
        String recortado = limpio.substring(0, maximo);
        int ultimoEspacio = recortado.lastIndexOf(' ');
        return (ultimoEspacio > 0 ? recortado.substring(0, ultimoEspacio) : recortado) + AVISO_CORTE;
    }
}
