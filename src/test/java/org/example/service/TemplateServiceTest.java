package org.example.service;

import org.example.exception.PlaceholderNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TemplateServiceTest {

    private static final String TEMPLATE = "Hi #{name}! Thank you for participating in our meeting about #{meeting_name}. Here is the link #{link} for joining us.";
    private final TemplateService templateService = new TemplateService();

    @Test
    void should_replace_placeholders_correctly() {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("name", "Jack");
        placeholders.put("meeting_name", "TDD in Java");
        placeholders.put("link", "https://meeting.com/tddJava");
        String expected = "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the link https://meeting.com/tddJava for joining us.";

        String result = templateService.replacePlaceholders(TEMPLATE, placeholders);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void should_throw_exception_when_placeholder_does_not_exists() {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("name", "Jack");
        placeholders.put("meeting_name", "TDD in Java");

        Assertions.assertThrows(PlaceholderNotFoundException.class,
                () -> templateService.replacePlaceholders(TEMPLATE, placeholders));
    }
}
