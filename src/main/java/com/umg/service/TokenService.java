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
            var lineFor = lineSplit[i].trim();
            var nextIndex = i + 1 < lineSplit.length ? i + 1 : i;

            var nextCharValue = lineSplit[nextIndex].trim();
            var validateEmpty = lineFor.isEmpty();
            var validateNextval = isOneSymbol(nextCharValue) && !validateEmpty;
            concatChar += cleanLine(lineFor);
            nextConcat += concatChar + nextCharValue;


            if (Utilities.GROUP_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.GROUP_SYMBOL));
                concatChar = "";
                nextConcat = "";
                continue;
            }

            if (Utilities.SEPARATOR_SYMBOL.contains(concatChar)) {
                tokens.add(new Token(concatChar, TokenType.SEPARATOR_SYMBOL));
                concatChar = "";
                nextConcat = "";
                continue;
            }

            if (Utilities.RELATIONAL_SYMBOL.contains(nextConcat)) {
                tokens.add(new Token(nextConcat, TokenType.RELATIONAL_SYMBOL));
                concatChar = "";
                nextConcat = "";
                i++;
                continue;
            }

            if (Utilities.INCREMENT_SYMBOL.contains(nextConcat)) {
                tokens.add(new Token(nextConcat, TokenType.INCREMENT_SYMBOL));
                nextConcat = "";
                concatChar = "";
                i++;
                continue;
            }

            if (validateEmpty || validateNextval || nextIndex == i) {
                if (validateNextval) {
                    concatChar = concatChar.replace(nextCharValue, "");
                }

                validate(concatChar, tokens);
                concatChar = "";
                nextConcat = "";
            }

        }

        result.setTokens(tokens);
        result.setLine(line);
    }


    public Boolean isOneSymbol(String nextCharValue) {
        return Utilities.GROUP_SYMBOL.contains(nextCharValue) ||
                Utilities.SEPARATOR_SYMBOL.contains(nextCharValue) ||
                Utilities.ASSIGNATION_SYMBOL.contains(nextCharValue);

    }

    public void validate(String concatChar, List<Token> tokens) {
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

    private String cleanLine(String value) {
        value = value.trim();
        return value.replaceAll("\\s", "");
    }

}
