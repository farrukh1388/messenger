package org.example;

import org.example.service.ConsoleService;
import org.example.service.FileService;
import org.example.service.MainService;
import org.example.service.TemplateService;

public class Main {

    public static void main(String[] args) {
        new MainService(new ConsoleService(), new TemplateService(), new FileService()).process(args);
    }
}