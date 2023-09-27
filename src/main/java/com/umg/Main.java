package com.umg;

import com.umg.service.FileService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        var fileService = new FileService();
        var results = fileService.processFile();

        for (var result : results) {
            System.out.println("--------------------");
            System.out.println("Linea original: " + result.getOriginalLine());
            result.getTokens().forEach(t -> {
                System.out.println("value [" + t.getValue() + "] token type [" + t.getType() + "]");
            });
            System.out.println("--------------------\n");
        }
    }
}