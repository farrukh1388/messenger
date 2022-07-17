package org.example.service;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileServiceTest {

    private static final String TEMPLATE = "Hi #{name}! Thank you for participating in our meeting about #{meeting_name}. Here is the link #{link} for joining us.";

    private final FileService fileService = new FileService();

    @Test
    @Tag("WorkingWithFileSystem")
    void should_read_template_from_file() {
        assertEquals(TEMPLATE, fileService.readTemplate("src/test/java/resources/testInput.txt"));
    }

    @Test
    @Tag("WorkingWithFileSystem")
    void should_throw_exception_while_reading_when_path_is_wrong() {
        IllegalStateException thrown =
                assertThrows(IllegalStateException.class, () -> fileService.readTemplate("whatever"));

        assertEquals("Can't find file", thrown.getMessage());
        assertEquals(FileNotFoundException.class, thrown.getCause().getClass());
    }

    @Test
    @Tag("WorkingWithFileSystem")
    void should_write_template_to_file() {
        fileService.writeToFile("src/test/java/resources/testOutput.txt", TEMPLATE);

        assertEquals(TEMPLATE, fileService.readTemplate("src/test/java/resources/testOutput.txt"));
    }
}