package com.umg.util;

import com.umg.type.TokenType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Utilities {

    public static final Map<TokenType, String> WORD_CLASSIFICATION = new HashMap<>();

    public static final List<String> RESERVED_SYMBOL = List.of("if", "then", "while", "for", "switch", "case", "when");
    public static final List<String> GROUP_SYMBOL = List.of("(", ")");
    public static final List<String> RELATIONAL_SYMBOL = List.of("<", "<=", ">", ">=", "==", "!=");
    public static final List<String> SEPARATOR_SYMBOL = List.of(";");
    public static final List<String> INCREMENT_SYMBOL = List.of("++");
    public static final List<String> ASSIGNATION_SYMBOL = List.of("=");

    public static boolean isNumber(String value) {
        return Pattern.matches("[0-9]+", value);
    }

    public static boolean isIdentification(String value) {
        return Pattern.matches("[a-z][a-z0-9]*", value);
    }

    public static void enterMapValues() {
        WORD_CLASSIFICATION.put(TokenType.RESERVED_SYMBOL, "Palabra reservada");
        WORD_CLASSIFICATION.put(TokenType.GROUP_SYMBOL, "Símbolo de agrupación");
        WORD_CLASSIFICATION.put(TokenType.RELATIONAL_SYMBOL, "Operador relacional");
        WORD_CLASSIFICATION.put(TokenType.SEPARATOR_SYMBOL, "Separador de sentencia");
        WORD_CLASSIFICATION.put(TokenType.INCREMENT_SYMBOL, "Operador de incremento");
        WORD_CLASSIFICATION.put(TokenType.ASSIGNATION_SYMBOL, "Operador de asignación");
        WORD_CLASSIFICATION.put(TokenType.NUMBER_SYMBOL, "Cadena numérica");
        WORD_CLASSIFICATION.put(TokenType.IDENTIFICATION_SYMBOL, "Identificador");
        WORD_CLASSIFICATION.put(TokenType.INVALID_SYMBOL, "Palabra con símbolos inválidos");
    }

    public static String getWordClassification(TokenType key) {
        return WORD_CLASSIFICATION.get(key);
    }
}
