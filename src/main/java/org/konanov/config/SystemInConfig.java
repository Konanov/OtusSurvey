package org.konanov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class SystemInConfig {

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }
}