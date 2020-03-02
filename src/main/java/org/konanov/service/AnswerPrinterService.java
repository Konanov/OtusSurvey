package org.konanov.service;

import org.konanov.domain.TestStage;

public class AnswerPrinterService {

    public void printAnswers(TestStage stage) {
        System.out.println(stage.getQuestion());
        System.out.println("--------------------------------------");
        System.out.println("Answers: ");
        System.out.println("1) " + stage.getFirstAnswer());
        System.out.println("2) " + stage.getSecondAnswer());
        System.out.println("3) " + stage.getThirdAnswer());
    }
}
