package com.umg;

import com.umg.dto.Result;
import com.umg.service.TokenService;

public class Main {
    public static void main(String[] args) {
        var service = new TokenService();
        var result = new Result();
        service.getResult("for (valini1= 0; valini1 <= 10; ++valini1)", result);

        result.getTokens().forEach(t -> {
            System.out.println("value [" + t.getValue() + "] token type [" + t.getType() + "]");
        });
    }
}