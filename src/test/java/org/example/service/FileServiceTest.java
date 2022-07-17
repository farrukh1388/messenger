package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileServiceTest {

    private static final String TEMPLATE = "Hi #{name}! Thank you for participating in our meeting about #{meeting_name}. Here is the link #{link} for joining us.";

    private final FileService fileService = new FileService("src/test/java/resources/input.txt");

    @Test
    void readTemplate() {
        assertEquals(TEMPLATE, fileService.readTemplate());
    }
}