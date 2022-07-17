package org.example.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileService {

    private final Scanner scanner;

    public FileService(String path) {
        try {
            this.scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Can't find file", e);
        }
    }

    public String readTemplate() {
        StringBuilder template = new StringBuilder();
        String startingAppender = "";
        while (scanner.hasNextLine()) {
            template.append(startingAppender).append(scanner.nextLine());
            startingAppender = "\n";
        }
        return template.toString();
    }
}
