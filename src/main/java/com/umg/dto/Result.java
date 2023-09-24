package com.umg.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Result {

    private String line;
    private String originalLine;
    private List<Token> tokens;

    public Result(String originalLine) {
        this.tokens = new ArrayList<>();
        this.originalLine = originalLine;
    }

}
