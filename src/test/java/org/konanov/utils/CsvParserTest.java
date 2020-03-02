package org.konanov.utils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvParserTest {

    private CsvParser parser;

    @Before
    public void setUp() {
        parser = new CsvParser();
    }

    @Test
    public void parser_shouldParseCsv() throws IOException {
        final List<String[]> readLines = parser.readQuestions("/questions.csv");
        assertThat(readLines.get(0)).isEqualTo(new String[]{"this", "is", "test", "data"});
    }

    @Test(expected = IOException.class)
    public void parser_shouldThrowErrorWhenFileNotFound() throws IOException {
        parser.readQuestions("/not_exist.csv");
    }
}