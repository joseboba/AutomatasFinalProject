package com.umg.util;

import java.util.List;

public class Utilities {

    public static final List<String> RESERVED_SYMBOL = List.of("if", "then", "while", "for", "switch", "case", "when");
    public static final List<String> GROUP_SYMBOL = List.of("(", ")");
    public static final List<String> RELATIONAL_SYMBOL = List.of("<", "<=", ">", ">=", "==", "!=");
    public static final List<String> SEPARATOR_SYMBOL = List.of(";");
    public static final List<String> INCREMENT_SYMBOL = List.of("++");
    public static final List<String> ASSIGNATION_SYMBOL = List.of("=");

}
