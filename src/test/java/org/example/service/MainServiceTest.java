package org.example.service;

import org.example.UnitTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MainServiceTest {

    private static final String TEMPLATE = "Hi #{name}! Thank you for participating in our meeting about #{meeting_name}. Here is the link #{link} for joining us.";

    @Mock
    private ConsoleService consoleService;
    @Mock
    private TemplateService templateService;
    @Mock
    private FileService fileService;
    @InjectMocks
    private MainService mainService;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(System.out);
    }

    @UnitTest
    @DisabledOnJre(JRE.OTHER)
    void when_arguments_count_is_zero() {
        String lineSeparator = System.getProperty("line.separator");

        Map<String, String> placeholders = new HashMap<>();
        String replaced = "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the link https://meeting.com/tddJava for joining us.";

        Mockito.when(consoleService.readTemplate()).thenReturn(TEMPLATE);
        Mockito.when(consoleService.readPlaceholders()).thenReturn(placeholders);
        Mockito.when(templateService.replacePlaceholders(TEMPLATE, placeholders)).thenReturn(replaced);

        mainService.process(new String[0]);

        assertEquals("Reading template from console" + lineSeparator + "Template after replacing placeholders:"
                + lineSeparator + "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the " +
                "link https://meeting.com/tddJava for joining us." + lineSeparator, outContent.toString());
    }

    @UnitTest
    @DisabledOnJre(JRE.OTHER)
    void when_arguments_count_is_two() {
        String lineSeparator = System.getProperty("line.separator");

        Map<String, String> placeholders = new HashMap<>();
        String replaced = "Hi Jack! Thank you for participating in our meeting about TDD in Java. Here is the link https://meeting.com/tddJava for joining us.";

        Mockito.when(fileService.readTemplate("input.txt")).thenReturn(TEMPLATE);
        Mockito.when(consoleService.readPlaceholders()).thenReturn(placeholders);
        Mockito.when(templateService.replacePlaceholders(TEMPLATE, placeholders)).thenReturn(replaced);

        String[] args = {"input.txt", "output.txt"};
        mainService.process(args);

        assertEquals("Reading template from file input.txt" + lineSeparator
                + "Template after replacing placeholders written to file output.txt" + lineSeparator, outContent.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4, 5, 10})
    @DisabledOnJre(JRE.OTHER)
    void should_throw_exception_while_args_count_not_zero_or_two(int count) {
        IllegalStateException thrown =
                assertThrows(IllegalStateException.class, () -> mainService.process(new String[count]));

        assertEquals("Unknown count of parameters", thrown.getMessage());
    }
}