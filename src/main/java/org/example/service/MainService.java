package org.example.service;

import java.util.Map;
import java.util.Scanner;

public class MainService {

    private final ConsoleService consoleService;
    private final TemplateService templateService;
    private final FileService fileService;

    public MainService(ConsoleService consoleService, TemplateService templateService, FileService fileService) {
        this.consoleService = consoleService;
        this.templateService = templateService;
        this.fileService = fileService;
    }

    public void process(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            consoleService.setScanner(scanner);
            if (args.length == 0) {

                System.out.println("Reading template from console");
                String template = consoleService.readTemplate();
                Map<String, String> placeholders = consoleService.readPlaceholders();

                String replaced = templateService.replacePlaceholders(template, placeholders);

                System.out.println("Template after replacing placeholders:");
                System.out.println(replaced);

            } else if (args.length == 2) {

                String inputPath = args[0];
                String outputPath = args[1];
                System.out.println("Reading template from file " + inputPath);

                String template = fileService.readTemplate(inputPath);
                Map<String, String> placeholders = consoleService.readPlaceholders();

                String replaced = templateService.replacePlaceholders(template, placeholders);

                fileService.writeToFile(outputPath, replaced);
                System.out.println("Template after replacing placeholders written to file " + outputPath);

            } else {
                throw new IllegalStateException("Unknown count of parameters");
            }
        }
    }
}
