package com.umg.util;

import java.util.List;
import java.util.regex.Pattern;

public class Utilities {

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

}
