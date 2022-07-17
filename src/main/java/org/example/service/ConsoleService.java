package org.example.service;

import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner;

    public ConsoleService(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readTemplate() {
        StringBuilder template = new StringBuilder();
        System.out.println("Please enter template");
        while (scanner.hasNextLine()) {
            template.append(scanner.nextLine());
        }
        return template.toString();
    }
}
