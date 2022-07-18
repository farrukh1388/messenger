package org.example.service;

import org.example.LoggingExtension;
import org.example.exception.PlaceholderNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LoggingExtension.class)
public class TemplateServiceTest {

    private static final String TEMPLATE = "Hi #{name}! Thank you for participating in our meeting about #{meeting_name}. Here is the link #{link} for joining us.";
    private final TemplateService templateService = new TemplateService();
    private Logger logger;

    @Test
    void should_replace_placeholders_correctly() {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("name", "Jack");
        placeholders.put("meeting_name", "TDD in Java");
        placeholders.put("link", "https://meeting.com/tddJava");
        String expected = "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the link https://meeting.com/tddJava for joining us.";

        String result = templateService.replacePlaceholders(TEMPLATE, placeholders);

        assertEquals(expected, result);
    }

    @Test
    void should_throw_exception_when_placeholder_does_not_exists() {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("name", "Jack");
        placeholders.put("meeting_name", "TDD in Java");
        logger.info(() -> "One placeholder missed");

        PlaceholderNotFoundException thrown = Assertions.assertThrows(PlaceholderNotFoundException.class,
                () -> templateService.replacePlaceholders(TEMPLATE, placeholders));

        logger.info(() -> "Thrown exception message: " + thrown.getMessage());
        assertEquals("Placeholder for link not found", thrown.getMessage());
    }

    @TestFactory
    Stream<DynamicTest> should_ignore_extra_placeholders() {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("name", "Jack");
        placeholders.put("meeting_name", "TDD in Java");
        placeholders.put("link", "https://meeting.com/tddJava");
        String expected = "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the link https://meeting.com/tddJava for joining us.";

        return IntStream.iterate(1, n -> n + 1).limit(5)
                .mapToObj(n -> DynamicTest.dynamicTest("test" + n,
                        () -> {
                            placeholders.put("name" + n, "value" + n);
                            assertEquals(expected, templateService.replacePlaceholders(TEMPLATE, placeholders));
                        }));
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
