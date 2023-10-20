package com.umg.util;

import com.umg.type.TipoToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Utilidades {

    public static final Map<TipoToken, String> CLASIFICACION_DE_PALABRA = new HashMap<>();

    public static final List<String> SIMBOLO_PALABRA_RESERVADA = List.of("if", "then", "while", "for", "switch", "case", "when");
    public static final List<String> SIMBOLO_AGRUPACION = List.of("(", ")");
    public static final List<String> SIMBOLO_RELACIONAL = List.of("<", "<=", ">", ">=", "==", "!=");
    public static final List<String> SIMBOLO_SEPARADOR = List.of(";");
    public static final List<String> SIMBOLO_INCREMENTO = List.of("++");
    public static final List<String> SIMBOLO_ASIGNACION = List.of("=");

    public static boolean esNumero(String valor) {
        return Pattern.matches("[0-9]+", valor);
    }

    public static boolean esIdentificador(String valor) {
        return Pattern.matches("[a-z][a-z0-9]*", valor);
    }

    public static void ingresarValoresMap() {
        CLASIFICACION_DE_PALABRA.put(TipoToken.SIMBOLO_PALABRA_RESERVADA, "Palabra reservada");
        CLASIFICACION_DE_PALABRA.put(TipoToken.SIMBOLO_AGRUPACION, "Símbolo de agrupación");
        CLASIFICACION_DE_PALABRA.put(TipoToken.SIMBOLO_RELACIONAL, "Operador relacional");
        CLASIFICACION_DE_PALABRA.put(TipoToken.SIMBOLO_SEPARADOR, "Separador de sentencia");
        CLASIFICACION_DE_PALABRA.put(TipoToken.SIMBOLO_INCREMENTO, "Operador de incremento");
        CLASIFICACION_DE_PALABRA.put(TipoToken.SIMBOLO_ASIGNACION, "Operador de asignación");
        CLASIFICACION_DE_PALABRA.put(TipoToken.SIMBOLO_NUMERICO, "Cadena numérica");
        CLASIFICACION_DE_PALABRA.put(TipoToken.SIMBOLO_IDENTIFICADOR, "Identificador");
        CLASIFICACION_DE_PALABRA.put(TipoToken.SIMBOLO_INVALIDO, "Cadena no válida");
    }

    public static String obtenerClasificacionDePalabra(TipoToken clave) {
        return CLASIFICACION_DE_PALABRA.get(clave);
    }
}
