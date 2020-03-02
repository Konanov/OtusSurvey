package org.konanov.service;

import org.konanov.domain.TestStage;

import java.util.Scanner;

public class LogicalCheckService {

    private int totalPoints = 0;

    public void checkAnswer(Scanner scanner, TestStage stage) {
        while (true) {
            final String answer = scanner.nextLine().trim();
            final boolean notFirstAnswer = !answer.equalsIgnoreCase(stage.getFirstAnswer());
            final boolean notSecondAnswer = !answer.equalsIgnoreCase(stage.getSecondAnswer());
            final boolean notThirdAnswer = !answer.equalsIgnoreCase(stage.getThirdAnswer());
            if (notFirstAnswer && notSecondAnswer && notThirdAnswer) {
                System.out.println("You've typed incorrect answer, please type it as shown in tips above");
            } else {
                if (!notFirstAnswer) {
                    totalPoints += stage.getFirstQuestionPoints();
                } else if (!notSecondAnswer) {
                    totalPoints += stage.getSecondQuestionPoints();
                } else {
                    totalPoints += stage.getThirdQuestionPoints();
                }
                break;
            }
        }
    }

    public int resumeSurvey() {
        return this.totalPoints;
    }
}
