package com.umg.service;

import com.umg.dto.Result;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {

    private final TokenService service = new TokenService();

    public List<Result> processFile() throws FileNotFoundException {
        var results = new ArrayList<Result>();
        var chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text", "txt", "csv"));
        int returnVal = chooser.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            analyzeFile(chooser.getSelectedFile(), results);
        }

        return results;
    }


    private void analyzeFile(File file, ArrayList<Result> results) throws FileNotFoundException {
        var scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var result = new Result(line);
            service.getResult(line, result);
            results.add(result);
        }

        scanner.close();
    }

}
