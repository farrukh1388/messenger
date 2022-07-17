package org.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

class ConsoleServiceTest {

    private static final String TEMPLATE = "Hi #{name}! Thank you for participating in our meeting about #{meeting_name}. Here is the link #{link} for joining us.";

    private final ConsoleService consoleService = new ConsoleService(new Scanner(TEMPLATE));

    @Test
    void should_read_template_from_console() {
        Assertions.assertEquals(TEMPLATE, consoleService.readTemplate());
    }
}