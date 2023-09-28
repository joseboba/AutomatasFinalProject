package com.umg;

import com.umg.service.FileService;
import com.umg.util.Utilities;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Utilities.enterMapValues();
        var fileService = new FileService();
        var results = fileService.processFile();

        for (var result : results) {
            System.out.println("--------------------");
            System.out.println("Sentencia: \n" + result.getOriginalLine());
            
            if(!result.getTokens().isEmpty()){
                    System.out.println("\nToken:");
                    result.getTokens().forEach(t -> {
                    System.out.println(t.getValue() + " \t" +  Utilities.getWordClassification(t.getType().toString()));
                });
                System.out.println("--------------------\n");
            }
            
        }


    }
}