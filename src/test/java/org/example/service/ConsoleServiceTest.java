package org.example.service;

import org.example.exception.PlaceholderFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Scanner;

class ConsoleServiceTest {

    private static final String TEMPLATE = "Hi #{name}! Thank you for participating in our meeting about #{meeting_name}. Here is the link #{link} for joining us.";
    private static final String PLACEHOLDERS = "name>Jack;meeting_name>TDD in Java;link>https://meeting.com/tddJava";

    private final ConsoleService consoleService = new ConsoleService();

    @Test
    void should_read_template_from_console() {
        try (Scanner scanner = new Scanner(TEMPLATE)) {
            consoleService.setScanner(scanner);
            Assertions.assertEquals(TEMPLATE, consoleService.readTemplate());
        }
    }

    @Test
    void should_read_placeholders_from_console() {
        try (Scanner scanner = new Scanner(PLACEHOLDERS)) {
            consoleService.setScanner(scanner);
            Assertions.assertEquals(3, consoleService.readPlaceholders().size());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"name:Jack,meeting_name:whatever", "name:Jack;meeting_name:whatever"})
    void should_throw_exception_when_placeholders_have_wrong_format(String param) {
        try (Scanner scanner = new Scanner(param)) {
            consoleService.setScanner(scanner);
            PlaceholderFormatException thrown = Assertions
                    .assertThrows(PlaceholderFormatException.class, consoleService::readPlaceholders);

            Assertions.assertEquals("Wrong placeholder format", thrown.getMessage());
        }
    }
}