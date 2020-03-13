package org.konanov.mapper;

import lombok.RequiredArgsConstructor;
import org.konanov.domain.TestStage;
import org.konanov.utils.CsvParser;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class StageMapper {

    private final MessageSource messageSource;

    private final Map<Integer, String> shortcuts = Map.of(
        0, "one", 1, "two", 2, "three", 3, "four", 4, "five"
    );

    public List<TestStage> convertToStages(Locale locale) {
        return IntStream.range(0, 5)
                .mapToObj(lineNumber -> new TestStage(
                        messageSource.getMessage("question." + shortcuts.get(lineNumber), null, locale),
                        messageSource.getMessage("answer." + shortcuts.get(lineNumber) + ".one", null, locale),
                        Integer.parseInt(messageSource.getMessage("score." + shortcuts.get(lineNumber) + ".one", null, locale)),
                        messageSource.getMessage("answer." + shortcuts.get(lineNumber) + ".two", null, locale),
                        Integer.parseInt(messageSource.getMessage("score." + shortcuts.get(lineNumber) + ".two", null, locale)),
                        messageSource.getMessage("answer." + shortcuts.get(lineNumber) + ".three", null, locale),
                        Integer.parseInt(messageSource.getMessage("score." + shortcuts.get(lineNumber) + ".three", null, locale))
                        )
                )
                .collect(toList());
    }
}
