package org.konanov;

import org.konanov.service.SurveyFacadeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@ComponentScan
@Configuration
public class Application {

    public static void main(String[] args) throws IOException {
        final ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        final SurveyFacadeService service = context.getBean(SurveyFacadeService.class);
        service.doSurvey();
    }
}
