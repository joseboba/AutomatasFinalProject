package com.umg.service;

import com.umg.dto.Result;
import com.umg.dto.Token;
import com.umg.type.TokenType;
import com.umg.util.Utilities;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class TokenService {

    public void decryptSymbolToken(String line, Result result) {
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


            if (Utilities.RELATIONAL_SYMBOL.contains(nextConcat)) {
                tokens.add(new Token(nextConcat, TokenType.RELATIONAL_SYMBOL));
                concatChar = "";
                i++;
                continue;
            }

            if (Utilities.INCREMENT_SYMBOL.contains(nextConcat)) {
                tokens.add(new Token(nextConcat, TokenType.INCREMENT_SYMBOL));
                concatChar = "";
                i++;
                continue;
            }

            if (Utilities.GROUP_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.GROUP_SYMBOL));
                concatChar = "";
                continue;
            }

            if (Utilities.SEPARATOR_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.SEPARATOR_SYMBOL));
                concatChar = "";
                continue;
            }

            if (Utilities.RELATIONAL_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.RELATIONAL_SYMBOL));
                concatChar = "";
                continue;
            }

            if (Utilities.INCREMENT_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.INCREMENT_SYMBOL));
                concatChar = "";
                continue;
            }

            if (Utilities.ASSIGNATION_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.INCREMENT_SYMBOL));
                concatChar = "";
                continue;
            }

            if (validateEmpty || validateNextVal || nextIndex == i) {
                if (validateNextVal) {
                    concatChar = concatChar.replace(nextCharValue, "");
                }

                validate(concatChar, tokens);
                concatChar = "";
                continue;
            }

            if (!canBeValid(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.INVALID_SYMBOL));
                concatChar = "";
            }

        }


        result.setTokens(tokens);
        result.setLine(line);
    }


    private Boolean isOneSymbol(String nextCharValue) {
        return Utilities.GROUP_SYMBOL.contains(nextCharValue) ||
                Utilities.SEPARATOR_SYMBOL.contains(nextCharValue) ||
                Utilities.ASSIGNATION_SYMBOL.contains(nextCharValue);

    }

    private void validate(String concatChar, List<Token> tokens) {
        if (Utilities.RESERVED_SYMBOL.contains(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.RESERVED_SYMBOL));
            return;
        }

        if (Utilities.RELATIONAL_SYMBOL.contains(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.RELATIONAL_SYMBOL));
            return;
        }

        if (Utilities.INCREMENT_SYMBOL.contains(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.INCREMENT_SYMBOL));
            return;
        }

        if (Utilities.ASSIGNATION_SYMBOL.contains(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.ASSIGNATION_SYMBOL));
            return;
        }

        if (Utilities.isNumber(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.NUMBER_SYMBOL));
            return;
        }

        if (Utilities.isIdentification(concatChar)) {
            tokens.add(new Token(concatChar, TokenType.IDENTIFICATION_SYMBOL));
        }
    }

    private boolean canBeValid(String value) {
        return (Utilities.RESERVED_SYMBOL.contains(value))
                || (Utilities.GROUP_SYMBOL.contains(value))
                || (Utilities.RELATIONAL_SYMBOL.contains(value))
                || (Utilities.SEPARATOR_SYMBOL.contains(value))
                || (Utilities.INCREMENT_SYMBOL.contains(value))
                || (Utilities.ASSIGNATION_SYMBOL.contains(value))
                || (Utilities.isNumber(value))
                || (Utilities.isIdentification(value));
    }

    private String cleanLine(String value) {
        value = value.trim();
        return value.replaceAll("\\s", "");
    }

}
