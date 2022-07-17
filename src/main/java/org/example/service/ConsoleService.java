package org.example.service;

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
}
