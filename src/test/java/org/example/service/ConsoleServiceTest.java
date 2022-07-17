package org.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

class ConsoleServiceTest {

    private static final String TEMPLATE = "Hi #{name}! Thank you for participating in our meeting about #{meeting_name}. Here is the link #{link} for joining us.";
    private static final String PLACEHOLDERS = "name>Jack;meeting_name>TDD in Java;link>https://meeting.com/tddJava";

    @Test
    void should_read_template_from_console() {
        ConsoleService consoleService = new ConsoleService(new Scanner(TEMPLATE));
        Assertions.assertEquals(TEMPLATE, consoleService.readTemplate());
    }

    @Test
    void should_read_placeholders_from_console() {
        ConsoleService consoleService = new ConsoleService(new Scanner(PLACEHOLDERS));
        Assertions.assertEquals(3, consoleService.readPlaceholders().size());
    }
}