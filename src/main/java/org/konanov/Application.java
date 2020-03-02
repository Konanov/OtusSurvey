package org.konanov;

import org.konanov.service.LogicalCheckService;
import org.konanov.service.QuestionProviderService;
import org.konanov.service.SurveyStageSelectorService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;

public class Application {

    private static final String START_SURVEY = "Hello, %s. Please answer following question to determine if you are a traitor";

    public static void main(String[] args) throws IOException {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        final QuestionProviderService questionProviderService = context.getBean(QuestionProviderService.class);
        final LogicalCheckService checkService = context.getBean(LogicalCheckService.class);
        final SurveyStageSelectorService stageSelectorService = context.getBean(SurveyStageSelectorService.class);

        final Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce yourself, please: ");
        final String userName = scanner.nextLine();
        System.out.println(format(START_SURVEY, userName));
        System.out.println("--------------------------------------");

        questionProviderService.createQuestions("/questions.csv");

        stageSelectorService.doQuestion(scanner, 0);
        stageSelectorService.doQuestion(scanner, 1);
        stageSelectorService.doQuestion(scanner, 2);
        stageSelectorService.doQuestion(scanner, 3);
        stageSelectorService.doQuestion(scanner, 4);

        final int totalPoints = checkService.resumeSurvey();
        System.out.println(userName + ", you are " + totalPoints + "% heretic");
    }
}
