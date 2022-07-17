package org.example.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateService {

    private static final String PATTERN = "#\\{(?<w>\\w+)}";

    public String replacePlaceholders(String template, Map<String, String> placeholders) {
        Matcher matcher = Pattern.compile(PATTERN).matcher(template);

        while (matcher.find()) {
            String match = matcher.group(1);
            String placeholder = placeholders.get(match);
            template = template.replace("#{" + match + "}", placeholder);
        }
        return template;
    }
}
