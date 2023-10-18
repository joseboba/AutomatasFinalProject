package com.umg.service;

import com.umg.dto.Resultado;
import com.umg.dto.Token;
import com.umg.type.TipoToken;
import com.umg.util.Utilidades;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ServicioDeToken {

    public void descifrarTokenDeSimbolo(String linea, Resultado resultado) {
        var tokens = resultado.getTokens();
        var elementosDeLinea = linea.split("");
        var charDeConcatenacion = "";
        var concatenacionSiguiente = "";

        for (int i = 0; i < elementosDeLinea.length; i++) {
            concatenacionSiguiente = "";
            var elemento = elementosDeLinea[i].trim();
            var indiceSiguiente = i + 1 < elementosDeLinea.length ? i + 1 : i;
            var valorCharSiguiente = elementosDeLinea[indiceSiguiente].trim();
            var validarVacio = elemento.isEmpty();
            var validarSiguienteValor = esSimboloUnico(valorCharSiguiente) && !validarVacio;
            charDeConcatenacion += limpiarLinea(elemento);
            concatenacionSiguiente += charDeConcatenacion + valorCharSiguiente;
            var esSimboloDecimal = indiceSiguiente + 1 < elementosDeLinea.length && esSimboloDecimal(indiceSiguiente, elementosDeLinea);

            if (!puedeSerValido(charDeConcatenacion) && !puedeSerValido(concatenacionSiguiente) && !validarVacio) {
                tokens.add(new Token(concatenacionSiguiente, TipoToken.SIMBOLO_INVALIDO));
                charDeConcatenacion = "";
                i++;
                continue;
            }

            if (Utilidades.SIMBOLO_RELACIONAL.contains(concatenacionSiguiente)) {
                tokens.add(new Token(concatenacionSiguiente, TipoToken.SIMBOLO_RELACIONAL));
                charDeConcatenacion = "";
                i++;
                continue;
            }

            if (Utilidades.SIMBOLO_INCREMENTO.contains(concatenacionSiguiente)) {
                tokens.add(new Token(concatenacionSiguiente, TipoToken.SIMBOLO_INCREMENTO));
                charDeConcatenacion = "";
                i++;
                continue;
            }

            if (Utilidades.SIMBOLO_AGRUPACION.contains(charDeConcatenacion)) {
                tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_AGRUPACION));
                charDeConcatenacion = "";
                continue;
            }

            if (Utilidades.SIMBOLO_SEPARADOR.contains(charDeConcatenacion)) {
                tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_SEPARADOR));
                charDeConcatenacion = "";
                continue;
            }

            if (Utilidades.SIMBOLO_RELACIONAL.contains(charDeConcatenacion)) {
                tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_RELACIONAL));
                charDeConcatenacion = "";
                continue;
            }

            if (Utilidades.SIMBOLO_INCREMENTO.contains(charDeConcatenacion)) {
                tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_INCREMENTO));
                charDeConcatenacion = "";
                continue;
            }

            if (Utilidades.SIMBOLO_ASIGNACION.contains(charDeConcatenacion)) {
                tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_ASIGNACION));
                charDeConcatenacion = "";
                continue;
            }

            if (esSimboloDecimal) {
                charDeConcatenacion = charDeConcatenacion.replace(valorCharSiguiente, "");
                validar(charDeConcatenacion, tokens);
                charDeConcatenacion = "";
            }

            if (validarVacio || validarSiguienteValor || indiceSiguiente == i) {
                if (validarSiguienteValor) {
                    charDeConcatenacion = charDeConcatenacion.replace(valorCharSiguiente, "");
                }

                validar(charDeConcatenacion, tokens);
                charDeConcatenacion = "";
            }

        }


        resultado.setTokens(tokens);
        resultado.setLinea(linea);
    }


    private Boolean esSimboloUnico(String valorSiguiente) {
        return Utilidades.SIMBOLO_AGRUPACION.contains(valorSiguiente) ||
                Utilidades.SIMBOLO_SEPARADOR.contains(valorSiguiente) ||
                Utilidades.SIMBOLO_ASIGNACION.contains(valorSiguiente);

    }

    private Boolean esSimboloDecimal(Integer enteroSiguiente, String[] elementosDeLinea) {
        var segundoValorSiguiente = elementosDeLinea[enteroSiguiente] + elementosDeLinea[enteroSiguiente + 1];
        return Utilidades.SIMBOLO_RELACIONAL.contains(segundoValorSiguiente)
                || Utilidades.SIMBOLO_INCREMENTO.contains(segundoValorSiguiente);
    }

    private void validar(String charDeConcatenacion, List<Token> tokens) {
        if (Utilidades.SIMBOLO_PALABRA_RESERVADA.contains(charDeConcatenacion)) {
            tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_PALABRA_RESERVADA));
            return;
        }

        if (Utilidades.SIMBOLO_RELACIONAL.contains(charDeConcatenacion)) {
            tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_RELACIONAL));
            return;
        }

        if (Utilidades.SIMBOLO_INCREMENTO.contains(charDeConcatenacion)) {
            tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_INCREMENTO));
            return;
        }

        if (Utilidades.SIMBOLO_ASIGNACION.contains(charDeConcatenacion)) {
            tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_ASIGNACION));
            return;
        }

        if (Utilidades.esNumero(charDeConcatenacion)) {
            tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_NUMERICO));
            return;
        }

        if (Utilidades.esIdentificador(charDeConcatenacion)) {
            tokens.add(new Token(charDeConcatenacion, TipoToken.SIMBOLO_IDENTIFICADOR));
        }
    }

    private boolean puedeSerValido(String valor) {
        return (Utilidades.SIMBOLO_PALABRA_RESERVADA.contains(valor))
                || (Utilidades.SIMBOLO_AGRUPACION.contains(valor))
                || (Utilidades.SIMBOLO_RELACIONAL.contains(valor))
                || (Utilidades.SIMBOLO_SEPARADOR.contains(valor))
                || (Utilidades.SIMBOLO_INCREMENTO.contains(valor))
                || (Utilidades.SIMBOLO_ASIGNACION.contains(valor))
                || (Utilidades.esNumero(valor))
                || (Utilidades.esIdentificador(valor));
    }

    private String limpiarLinea(String valor) {
        valor = valor.trim();
        return valor.replaceAll("\\s", "");
    }

}
