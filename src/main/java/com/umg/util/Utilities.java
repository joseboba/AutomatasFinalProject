package com.umg.util;

import java.util.List;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;

public class Utilities {

    public static final Map<String,String> WORD_CLASSIFICATION = new HashMap<>();
    
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

     public static void enterMapValues () {
        WORD_CLASSIFICATION.put("RESERVED_SYMBOL","Palabra reservada");
        WORD_CLASSIFICATION.put("GROUP_SYMBOL","Símbolo de agrupación");
        WORD_CLASSIFICATION.put("RELATIONAL_SYMBOL","Operador relacional");
        WORD_CLASSIFICATION.put("SEPARATOR_SYMBOL","Separador de sentencia");
        WORD_CLASSIFICATION.put("INCREMENT_SYMBOL","Operador de incremento");
        WORD_CLASSIFICATION.put("ASSIGNATION_SYMBOL","Operador de asignación");
        WORD_CLASSIFICATION.put("NUMBER_SYMBOL","Operador de asignación");
        WORD_CLASSIFICATION.put("IDENTIFICATION_SYMBOL","Identificador");
    }
    
    public static String getWordClassification(String key){
        return WORD_CLASSIFICATION.get(key);
    }
}
