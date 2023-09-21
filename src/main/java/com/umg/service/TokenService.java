package com.umg.service;

import com.umg.dto.Result;
import com.umg.dto.Token;
import com.umg.type.TokenType;
import com.umg.util.Utilities;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class TokenService {

    private void decryptSymbolToken(String line, Result result) {
        var tokens = result.getTokens();
        for (int i = 0; i < line.length(); i++) {
            var charValue = line.charAt(i);

            if (Character.isWhitespace(charValue)) continue;
            var charStringValue = Character.toString(charValue);

            if (Utilities.GROUP_SYMBOL.contains(charStringValue)) {
                tokens.add(new Token(charStringValue, TokenType.GROUP_SYMBOL));
                line = line.replace(charStringValue, "");
                continue;
            }

            if (Utilities.SEPARATOR_SYMBOL.contains(charStringValue)) {
                tokens.add(new Token(charStringValue, TokenType.SEPARATOR_SYMBOL));
                line = line.replace(charStringValue, "");
            }
        }

        result.setTokens(tokens);
        result.setLine(line);
    }

    public void getResult(String line, Result result) {
        decryptSymbolToken(line, result);
        var tokens = result.getTokens();
        var resultSplitLine = cleanLine(result.getLine());

        line = result.getLine();
        for (var value : resultSplitLine) {
            if (Utilities.RESERVED_SYMBOL.contains(value)) {
                tokens.add(new Token(value, TokenType.RESERVED_SYMBOL));
                line = line.replace(value, "");
                continue;
            }

            if (Utilities.RELATIONAL_SYMBOL.contains(value)) {
                tokens.add(new Token(value, TokenType.RELATIONAL_SYMBOL));
                line = line.replace(value, "");
                continue;
            }

            if (Utilities.INCREMENT_SYMBOL.contains(value)) {
                tokens.add(new Token(value, TokenType.INCREMENT_SYMBOL));
                line = line.replace(value, "");
                continue;
            }

            if (Utilities.ASSIGNATION_SYMBOL.contains(value)) {
                tokens.add(new Token(value, TokenType.ASSIGNATION_SYMBOL));
                line = line.replace(value, "");
                continue;
            }

            if (Utilities.isNumber(value)) {
                tokens.add(new Token(value, TokenType.NUMBER_SYMBOL));
                line = line.replace(value, "");
                continue;
            }

            if (Utilities.isIdentification(value)) {
                tokens.add(new Token(value, TokenType.IDENTIFICATION_SYMBOL));
                line = line.replace(value, "");
            }
        }

        result.setTokens(tokens);
        result.setLine(line);
        if (line.trim().length() > 0) {
            getResult(line, result);
        }
    }

    private List<String> cleanLine(String line) {
        var splitResults = line.split(" ");
        for (int i = 0; i < splitResults.length; i++) {
            var value = splitResults[i].trim();
            splitResults[i] = value.replaceAll("\\s", "");
        }

        return Arrays.stream(splitResults).toList();
    }


}
