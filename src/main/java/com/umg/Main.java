package com.umg;

import java.io.IOException;
import java.util.ArrayList;
import com.umg.service.FileService;
import com.umg.ui.PantallaResultado;
import com.umg.util.Utilities;

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
        Utilities.enterMapValues();
        var fileService = new FileService();
        var results = fileService.processFile();
        ArrayList<String> resultadoAMostrar = new ArrayList();

        if(!results.isEmpty()){
           for (var result : results) {
                resultadoAMostrar.add("\n ------------------------------------------- ");
                resultadoAMostrar.add("\n Sentencia: \n\n " + result.getOriginalLine() + "\n");
                if(!result.getTokens().isEmpty()){
                        resultadoAMostrar.add("\n Token: \n");
                        result.getTokens().forEach(t -> {
                        resultadoAMostrar.add("\n " + t.getValue() + " \t" +  Utilities.getWordClassification(t.getType()));
                    });
                }
                resultadoAMostrar.add("\n ------------------------------------------- \n");
            } 
          return resultadoAMostrar;
        }
        
        return null;

    }
}