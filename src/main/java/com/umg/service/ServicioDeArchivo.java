package com.umg.service;

import com.umg.dto.Resultado;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicioDeArchivo {

    private final ServicioDeToken servicio = new ServicioDeToken();

    public List<Resultado> procesarArchivo() throws IOException {
        var resultados = new ArrayList<Resultado>();
        var seleccionador = new JFileChooser();
        seleccionador.setFileFilter(new FileNameExtensionFilter("Text", "txt", "csv"));
        int valorDeRetorno = seleccionador.showOpenDialog(null);
        if (valorDeRetorno == JFileChooser.APPROVE_OPTION) {
            analizarArchivo(seleccionador.getSelectedFile(), resultados);
        }

        return resultados;
    }


    private void analizarArchivo(File archivo, ArrayList<Resultado> resultados) throws IOException {
        var escaner = new Scanner(archivo, StandardCharsets.UTF_8);

        while (escaner.hasNextLine()) {
            var linea = escaner.nextLine();
            if (linea.isEmpty()) continue;
            var resultado = new Resultado(linea);
            servicio.descifrarTokenDeSimbolo(linea.trim(), resultado);
            resultados.add(resultado);
        }

        escaner.close();
    }

}
