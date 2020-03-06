package org.konanov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;

@Service
@Configuration
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class SurveyFacadeService {

    private static final String START_SURVEY = "Hello, %s. Please answer following question to determine if you are a traitor";

    private final QuestionProviderService questionProviderService;
    private final SurveyStageSelectorService stageSelectorService;
    private final LogicalCheckService logicalCheckService;
    private final Scanner scanner;

    @Value("${survey.questionPath}")
    private String questionPath;

    @Value("${traitor.threshold}")
    private int threshold;

    private String doIntroduction() {
        System.out.println("Introduce yourself, please: ");
        final String userName = scanner.nextLine();
        System.out.println(format(START_SURVEY, userName));
        System.out.println("--------------------------------------");
        return userName;
    }

    private void createQuestions() throws IOException {
        questionProviderService.createQuestions(this.questionPath);
    }

    public void doSurvey() throws IOException {
        final String userName = doIntroduction();
        createQuestions();
        stageSelectorService.doQuestion(0);
        stageSelectorService.doQuestion(1);
        stageSelectorService.doQuestion(2);
        stageSelectorService.doQuestion(3);
        stageSelectorService.doQuestion(4);
        final int totalPoints = logicalCheckService.resumeSurvey();
        System.out.println(userName + ", you are " + totalPoints + "% heretic");

        if (totalPoints <= threshold) {
            System.out.println("Sorry, you have to be exterminated... Extermination process started");
        }
    }
}
