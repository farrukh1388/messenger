package org.example.service;

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
        return new HashMap<>();
    }
}
