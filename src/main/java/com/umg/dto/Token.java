package com.umg.dto;

import com.umg.type.TipoToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    private String valor;
    private TipoToken tipo;

}
