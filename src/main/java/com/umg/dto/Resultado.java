package com.umg.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Resultado {

    private String linea;
    private String lineaOriginal;
    private List<Token> tokens;

    public Resultado(String lineaOriginal) {
        this.tokens = new ArrayList<>();
        this.lineaOriginal = lineaOriginal;
    }

}
