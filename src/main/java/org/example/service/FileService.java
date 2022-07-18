package org.example.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileService {

    public String readTemplate(String path) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Can't find file", e);
        }
        StringBuilder template = new StringBuilder();
        String startingAppender = "";
        while (scanner.hasNextLine()) {
            template.append(startingAppender).append(scanner.nextLine());
            startingAppender = "\n";
        }
        scanner.close();
        return template.toString();
    }

    public void writeToFile(String path, String text) {
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(text.getBytes());
        } catch (IOException e) {
            System.err.println("Error writing to file " + path);
            throw new IllegalStateException("Error during write to file " + path);
        }
    }
}
