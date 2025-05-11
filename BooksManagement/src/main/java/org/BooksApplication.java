package org;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(scanBasePackages = {"org.components", "org.services", "org.entities", "org.controllers", "org.repositories"})
public class BooksApplication {
    @Value("${library.management.url}")
    public String libraryManagementUrl;


    public static void main(String[] args) {
        SpringApplication.run(BooksApplication.class, args);
    }

    @Bean
    @Qualifier("bookAPI")
    public RestTemplate restTemplate() {
        System.out.println("Initializing RestTemplate with URL: " + libraryManagementUrl);
        return new RestTemplateBuilder()
                .rootUri(libraryManagementUrl)
                .build();
    }
}

