package org.konanov.service;

import lombok.RequiredArgsConstructor;
import org.konanov.domain.TestStage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyStageSelectorService {

    private final QuestionProviderService questionProviderService;
    private final AnswerPrinterService answerPrinterService;
    private final LogicalCheckService checkService;

    public void doQuestion(int questionOrder) {
        final TestStage stage = questionProviderService.getStageByNumber(questionOrder);
        answerPrinterService.printAnswers(stage);
        checkService.checkAnswer(stage);
    }
}
