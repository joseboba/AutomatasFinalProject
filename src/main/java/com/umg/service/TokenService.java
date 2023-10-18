package com.umg.service;

import com.umg.dto.Resultado;
import com.umg.dto.Token;
import com.umg.type.TokenType;
import com.umg.util.Utilidades;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class TokenService {

    public void decryptSymbolToken(String line, Resultado result) {
        var tokens = result.getTokens();
        var lineSplit = line.split("");
        var concatChar = "";
        var nextConcat = "";

        for (int i = 0; i < lineSplit.length; i++) {
            nextConcat = "";
            var lineFor = lineSplit[i].trim();
            var nextIndex = i + 1 < lineSplit.length ? i + 1 : i;
            var nextCharValue = lineSplit[nextIndex].trim();
            var validateEmpty = lineFor.isEmpty();
            var validateNextVal = isOneSymbol(nextCharValue) && !validateEmpty;
            concatChar += cleanLine(lineFor);
            nextConcat += concatChar + nextCharValue;
            var isDoubleNextIsDoubleSymbol = nextIndex + 1 < lineSplit.length && doubleNextIsDoubleSymbol(nextIndex, lineSplit);

            if (!canBeValid(concatChar) && !canBeValid(nextConcat) && !validateEmpty) {
                tokens.add(new Token(nextConcat, TokenType.INVALID_SYMBOL));
                concatChar = "";
                i++;
                continue;
            }

            if (Utilidades.RELATIONAL_SYMBOL.contains(nextConcat)) {
                tokens.add(new Token(nextConcat, TokenType.RELATIONAL_SYMBOL));
                concatChar = "";
                i++;
                continue;
            }

            if (Utilidades.INCREMENT_SYMBOL.contains(nextConcat)) {
                tokens.add(new Token(nextConcat, TokenType.INCREMENT_SYMBOL));
                concatChar = "";
                i++;
                continue;
            }

            if (Utilidades.GROUP_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.GROUP_SYMBOL));
                concatChar = "";
                continue;
            }

            if (Utilidades.SEPARATOR_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.SEPARATOR_SYMBOL));
                concatChar = "";
                continue;
            }

            if (Utilidades.RELATIONAL_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.RELATIONAL_SYMBOL));
                concatChar = "";
                continue;
            }

            if (Utilidades.INCREMENT_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.INCREMENT_SYMBOL));
                concatChar = "";
                continue;
            }

            if (Utilidades.ASSIGNATION_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.ASSIGNATION_SYMBOL));
                concatChar = "";
                continue;
            }

            if (isDoubleNextIsDoubleSymbol) {
                concatChar = concatChar.replace(nextCharValue, "");
                validate(concatChar, tokens);
                concatChar = "";
            }

            if (validateEmpty || validateNextVal || nextIndex == i) {
                if (validateNextVal) {
                    concatChar = concatChar.replace(nextCharValue, "");
                }

                validate(concatChar, tokens);
                concatChar = "";
            }

        }


        result.setTokens(tokens);
        result.setLinea(line);
    }


    private Boolean isOneSymbol(String nextCharValue) {
        return Utilidades.GROUP_SYMBOL.contains(nextCharValue) ||
                Utilidades.SEPARATOR_SYMBOL.contains(nextCharValue) ||
                Utilidades.ASSIGNATION_SYMBOL.contains(nextCharValue);

    }

    private Boolean doubleNextIsDoubleSymbol(Integer nextI, String[] lineSplit) {
        var secondNextValue = lineSplit[nextI] + lineSplit[nextI + 1];
        return Utilidades.RELATIONAL_SYMBOL.contains(secondNextValue)
                || Utilidades.INCREMENT_SYMBOL.contains(secondNextValue);
    }

    private void validate(String concatChar, List<Token> tokens) {
        if (Utilidades.RESERVED_SYMBOL.contains(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.RESERVED_SYMBOL));
            return;
        }

        if (Utilidades.RELATIONAL_SYMBOL.contains(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.RELATIONAL_SYMBOL));
            return;
        }

        if (Utilidades.INCREMENT_SYMBOL.contains(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.INCREMENT_SYMBOL));
            return;
        }

        if (Utilidades.ASSIGNATION_SYMBOL.contains(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.ASSIGNATION_SYMBOL));
            return;
        }

        if (Utilidades.isNumber(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.NUMBER_SYMBOL));
            return;
        }

        if (Utilidades.isIdentification(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.IDENTIFICATION_SYMBOL));
        }
    }

    private boolean canBeValid(String value) {
        return (Utilidades.RESERVED_SYMBOL.contains(value))
                || (Utilidades.GROUP_SYMBOL.contains(value))
                || (Utilidades.RELATIONAL_SYMBOL.contains(value))
                || (Utilidades.SEPARATOR_SYMBOL.contains(value))
                || (Utilidades.INCREMENT_SYMBOL.contains(value))
                || (Utilidades.ASSIGNATION_SYMBOL.contains(value))
                || (Utilidades.isNumber(value))
                || (Utilidades.isIdentification(value));
    }

    private String cleanLine(String value) {
        value = value.trim();
        return value.replaceAll("\\s", "");
    }

}
