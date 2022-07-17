package org.example.service;

import org.example.exception.PlaceholderFormatException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner;

    public ConsoleService(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readTemplate() {
        System.out.println("Please enter template:");
        return scanner.nextLine();
    }

    public Map<String, String> readPlaceholders() {
        System.out.println("Please enter placeholders like: first_name>first_value;second_name>second_value;...;last_name>last_value;");
        String line = scanner.nextLine();
        String[] nameValuePairs = line.split(";");
        Map<String, String> placeholders = new HashMap<>();
        for (String pair : nameValuePairs) {
            String[] keyValue = pair.split(">");
            if (keyValue.length != 2) {
                throw new PlaceholderFormatException("Wrong placeholder format");
            }
            placeholders.put(keyValue[0], keyValue[1]);
        }
        return placeholders;
    }
}
