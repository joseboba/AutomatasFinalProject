package com.umg;

import com.umg.dto.Result;
import com.umg.service.TokenService;

public class Main {
    public static void main(String[] args) {
        var service = new TokenService();
        var result = new Result();
        service.getResult("while (dato1 != 1000)", result);

        result.getTokens().forEach(t -> {
            System.out.println("value [" + t.getValue() + "] token type [" + t.getType() + "]");
        });
    }
}