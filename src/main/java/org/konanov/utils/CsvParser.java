package org.konanov.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class CsvParser {

    public List<String[]> readQuestions(String questionsPath) throws IOException {
        final Resource resource = new ClassPathResource(questionsPath);
        final List<String> lines = Files.readAllLines(resource.getFile().toPath());
        return lines.stream()
                .map(line -> line.split(":"))
                .collect(Collectors.toList());
    }
}
