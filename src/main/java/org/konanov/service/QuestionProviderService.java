package org.konanov.service;

import lombok.RequiredArgsConstructor;
import org.konanov.domain.TestStage;
import org.konanov.mapper.StageMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class QuestionProviderService {

    private final StageMapper mapper;
    private final List<TestStage> stages = new ArrayList<>();

    public void createQuestions(Locale locale) throws IOException {
        stages.addAll(questionsFromResources(locale));
    }

    private List<TestStage> questionsFromResources(Locale locale) throws IOException {
        return mapper.convertToStages(locale);
    }

    public TestStage getStageByNumber(int order) {
        return stages.get(order);
    }
}
