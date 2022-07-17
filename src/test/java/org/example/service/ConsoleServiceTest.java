package org.example.service;

import org.example.exception.PlaceholderFormatException;
import org.example.exception.PlaceholderNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
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

    @Test
    void should_throw_exception_when_placeholders_have_wrong_format() {
        ConsoleService consoleService = new ConsoleService(new Scanner("name:Jack,meeting_name:whatever"));
        Assertions.assertThrows(PlaceholderFormatException.class, consoleService::readPlaceholders);
    }
}