package com.umg.service;

import com.umg.dto.Result;
import com.umg.dto.Token;
import com.umg.type.TokenType;
import com.umg.util.Utilities;
import lombok.NoArgsConstructor;

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
                line = parseToBlank(line, i);
            }

            if (Utilities.SEPARATOR_SYMBOL.contains(charStringValue)) {
                tokens.add(new Token(charStringValue, TokenType.SEPARATOR_SYMBOL));
                line = parseToBlank(line, i);
            }
        }

        result.setTokens(tokens);
        result.setLine(line);
    }

    public void getResult(String line, Result result) {
        decryptSymbolToken(line, result);
        var tokens = result.getTokens();
        var resultSplitLine = cleanLine(line);

        for (int i = 0; i < resultSplitLine.size(); i++) {
            var value = resultSplitLine.get(i);

            if (Utilities.RESERVED_SYMBOL.contains(value)) {
                tokens.add(new Token(value, TokenType.RESERVED_SYMBOL));
                line = parseToBlank(line, i);
            }

            if (Utilities.RELATIONAL_SYMBOL.contains(value)) {
                tokens.add(new Token(value, TokenType.RELATIONAL_SYMBOL));
                line = parseToBlank(line, i);
            }

            if (Utilities.INCREMENT_SYMBOL.contains(value)) {
                tokens.add(new Token(value, TokenType.INCREMENT_SYMBOL));
                line = parseToBlank(line, i);
            }

            if (Utilities.ASSIGNATION_SYMBOL.contains(value)) {
                tokens.add(new Token(value, TokenType.ASSIGNATION_SYMBOL));
                line = parseToBlank(line, i);
            }
        }

        System.out.println("line length " + line.length());
        result.setTokens(tokens);
        result.setLine(line);
        if (line.trim().length() > 0) {
            decryptSymbolToken(line, result);
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

    private String parseToBlank(String original, Integer index) {
        return original.substring(0, index) + original.substring(index + 1);
    }

}
