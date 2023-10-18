package com.umg;

import java.io.IOException;
import java.util.ArrayList;
import com.umg.service.ServicioDeArchivo;
import com.umg.ui.PantallaResultado;
import com.umg.util.Utilidades;

public class Main {
    

    public static void main(String[] args) throws IOException {
        inicioDeAplicacion();
    }
    
    public static void inicioDeAplicacion() throws IOException {
        PantallaResultado pantallaResultado = new PantallaResultado();
        ArrayList<String> resultadoLecturaArchivo =  lecturaDeArchivo();
        if(resultadoLecturaArchivo != null){
            pantallaResultado.asignacionDeResultado(resultadoLecturaArchivo);
            pantallaResultado.setTitle("Analizador léxico");
            pantallaResultado.setVisible(true);
        }
    }
    
    public static ArrayList<String> lecturaDeArchivo() throws IOException {
        Utilidades.ingresarValoresMap();
        var servicioDeArchivo = new ServicioDeArchivo();
        var resultados = servicioDeArchivo.procesarArchivo();
        var resultadoAMostrar = new ArrayList<String>();

        if(!resultados.isEmpty()){
           for (var resultado : resultados) {
                resultadoAMostrar.add("\n ------------------------------------------- ");
                resultadoAMostrar.add("\n Sentencia: \n\n " + resultado.getLineaOriginal() + "\n");
                if(!resultado.getTokens().isEmpty()){
                        resultadoAMostrar.add("\n Token: \n");
                        resultado.getTokens().forEach(t -> {
                        resultadoAMostrar.add("\n " + t.getValor() + " \t" +  Utilidades.obtenerClasificacionDePalabra(t.getTipo()));
                    });
                }
                resultadoAMostrar.add("\n ------------------------------------------- \n");
            }
          return resultadoAMostrar;
        }
        
        return null;

    }
}