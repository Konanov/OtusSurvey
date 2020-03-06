package org.konanov.mapper;

import org.konanov.domain.TestStage;
import org.konanov.utils.CsvParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class StageMapper {
    private CsvParser parser;

    public StageMapper(CsvParser parser) {
        this.parser = parser;
    }

    public List<TestStage> convertToStages(String questionsPath) throws IOException {
        final List<String[]> lines = parser.readQuestions(questionsPath);
        return lines.stream()
                .map(line -> new TestStage(
                        line[0],
                        line[1],
                        Integer.parseInt(line[2]),
                        line[3],
                        Integer.parseInt(line[4]),
                        line[5],
                        Integer.parseInt(line[6]))
                )
                .collect(toList());
    }
}
