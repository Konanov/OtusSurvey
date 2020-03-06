package org.konanov.service;

import lombok.RequiredArgsConstructor;
import org.konanov.domain.TestStage;
import org.konanov.mapper.StageMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionProviderService {

    private final StageMapper mapper;
    private final List<TestStage> stages = new ArrayList<>();

    public void createQuestions(String questionPath) throws IOException {
        stages.addAll(questionsFromResources(questionPath));
    }

    private List<TestStage> questionsFromResources(String questionPath) throws IOException {
        return mapper.convertToStages(questionPath);
    }

    public TestStage getStageByNumber(int order) {
        return stages.get(order);
    }
}
