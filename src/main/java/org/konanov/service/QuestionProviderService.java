package org.konanov.service;

import org.konanov.domain.TestStage;
import org.konanov.mapper.StageMapper;

import java.io.IOException;
import java.util.List;

public class QuestionProviderService {

    private StageMapper mapper;
    private List<TestStage> stages;

    public QuestionProviderService(StageMapper mapper) {
        this.mapper = mapper;
    }

    public void createQuestions(String questionPath) throws IOException {
        this.stages = mapper.convertToStages(questionPath);
    }

    public TestStage getStageByNumber(int order) {
        return stages.get(order);
    }
}
