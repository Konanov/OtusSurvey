package org.konanov.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class LocalizationConfig {

    @Bean
    MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
        bundle.setBasename("i18n/bundle/messages");
        bundle.setDefaultEncoding("windows-1251");
        return bundle;
    }
}
