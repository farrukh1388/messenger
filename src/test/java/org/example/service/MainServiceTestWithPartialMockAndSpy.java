package org.example.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainServiceWithPartialMockAndSpyTest {

    private static final String TEMPLATE = "Hi #{name}! Thank you for participating in our meeting about #{meeting_name}. Here is the link #{link} for joining us.";
    private static final String PLACEHOLDERS = "name>Jack;meeting_name>TDD in Java;link>https://meeting.com/tddJava";

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream(PLACEHOLDERS.getBytes());

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @AfterEach
    public void restoreStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    void when_arguments_count_is_zero_with_partial_mock() {
        ConsoleService consoleService = Mockito.mock(ConsoleService.class, Mockito.CALLS_REAL_METHODS);
        TemplateService templateService = Mockito.mock(TemplateService.class);
        FileService fileService = Mockito.mock(FileService.class);
        MainService mainService = new MainService(consoleService, templateService, fileService);

        String lineSeparator = System.getProperty("line.separator");
        String replaced = "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the link https://meeting.com/tddJava for joining us.";

        Mockito.doReturn(TEMPLATE).when(consoleService).readTemplate(); // Mocked call
        Mockito.doCallRealMethod().when(consoleService).readPlaceholders(); // Real call
        Mockito.when(templateService.replacePlaceholders(Mockito.eq(TEMPLATE), Mockito.any())).thenReturn(replaced);

        mainService.process(new String[0]);

        assertEquals("Reading template from console" + lineSeparator
                + "Please enter placeholders like: first_name>first_value;second_name>second_value;...;last_name>last_value;"
                + lineSeparator + "Template after replacing placeholders:"
                + lineSeparator + "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the "
                + "link https://meeting.com/tddJava for joining us." + lineSeparator, outContent.toString());
    }

    @Test
    void when_arguments_count_is_zero_with_spy() {
        ConsoleService consoleService = Mockito.spy(ConsoleService.class);
        TemplateService templateService = Mockito.mock(TemplateService.class);
        FileService fileService = Mockito.mock(FileService.class);
        MainService mainService = new MainService(consoleService, templateService, fileService);

        String lineSeparator = System.getProperty("line.separator");
        String replaced = "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the link https://meeting.com/tddJava for joining us.";

        Mockito.doReturn(TEMPLATE).when(consoleService).readTemplate(); // Mocked call
        Mockito.doCallRealMethod().when(consoleService).readPlaceholders(); // Real call
        Mockito.when(templateService.replacePlaceholders(Mockito.eq(TEMPLATE), Mockito.any())).thenReturn(replaced);

        mainService.process(new String[0]);

        assertEquals("Reading template from console" + lineSeparator
                + "Please enter placeholders like: first_name>first_value;second_name>second_value;...;last_name>last_value;"
                + lineSeparator + "Template after replacing placeholders:"
                + lineSeparator + "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the "
                + "link https://meeting.com/tddJava for joining us." + lineSeparator, outContent.toString());
    }
}
