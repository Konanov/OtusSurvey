package org.konanov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

@Service
@Configuration
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class SurveyFacadeService {

    private final QuestionProviderService questionProviderService;
    private final SurveyStageSelectorService stageSelectorService;
    private final LogicalCheckService logicalCheckService;
    private final Scanner scanner;
    private final MessageSource messageSource;

    @Value("${survey.questionPath}")
    private String questionPath;

    @Value("${traitor.threshold}")
    private int threshold;

    private Locale selectLocale() {
        System.out.println("Please choose locale to use in survey (ENG/RUS): ");
        while (true) {
            final String locale = scanner.nextLine();
            System.out.println("--------------------------------------");
            if (!"RUS".equalsIgnoreCase(locale) && !"ENG".equalsIgnoreCase(locale)) {
                System.out.println("Please choose from RUS or ENG");
            } else {
                return "ENG".equalsIgnoreCase(locale) ? Locale.ENGLISH : Locale.getDefault();
            }
        }
    }

    private String doIntroduction(Locale locale) {
        final String userName;
        System.out.println(getMessage("greeting.message", null, locale));
        userName = scanner.nextLine();
        System.out.println(getMessage("start.survey", userName, locale));
        System.out.println("--------------------------------------");
        return userName;
    }

    private String getMessage(String property, String userName, Locale locale) {
        return messageSource.getMessage(property, new String[]{userName}, locale);
    }

    private void createQuestions(Locale locale) throws IOException {
        questionProviderService.createQuestions(locale);
    }

    public void doSurvey() throws IOException {
        final Locale locale = selectLocale();
        final String userName = doIntroduction(locale);
        createQuestions(locale);
        stageSelectorService.doQuestion(0);
        stageSelectorService.doQuestion(1);
        stageSelectorService.doQuestion(2);
        stageSelectorService.doQuestion(3);
        stageSelectorService.doQuestion(4);
        final int totalPoints = logicalCheckService.resumeSurvey();
        System.out.println(messageSource.getMessage("you.are.heretic", new String[]{userName, String.valueOf(totalPoints)}, locale));

        if (totalPoints >= threshold) {
            System.out.println(messageSource.getMessage("extermination.start", null, locale));
        }
    }
}
