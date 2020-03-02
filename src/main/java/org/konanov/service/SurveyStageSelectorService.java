package org.konanov.service;

import lombok.RequiredArgsConstructor;
import org.konanov.domain.TestStage;

import java.util.Scanner;

@RequiredArgsConstructor
public class SurveyStageSelectorService {

    private final QuestionProviderService questionProviderService;
    private final AnswerPrinterService answerPrinterService;
    private final LogicalCheckService checkService;

    public void doQuestion(Scanner scanner, int questionOrder) {
        final TestStage stage = questionProviderService.getStageByNumber(questionOrder);
        questionProviderService.getStageByNumber(questionOrder);
        answerPrinterService.printAnswers(stage);
        checkService.checkAnswer(scanner, stage);
    }
}
