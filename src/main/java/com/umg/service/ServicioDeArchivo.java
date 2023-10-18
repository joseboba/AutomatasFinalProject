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

    private final TokenService service = new TokenService();

    public List<Resultado> procesarArchivo() throws IOException {
        var results = new ArrayList<Resultado>();
        var chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text", "txt", "csv"));
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            analyzeFile(chooser.getSelectedFile(), results);
        }

        return results;
    }


    private void analyzeFile(File file, ArrayList<Resultado> results) throws IOException {
        var scanner = new Scanner(file, StandardCharsets.UTF_8);

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var result = new Resultado(line);
            service.decryptSymbolToken(line.trim(), result);
            results.add(result);
        }

        scanner.close();
    }

}
