package org;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(scanBasePackages = {"org.repositories", "org.services", "org.entities", "org.controllers", "org.components"})
public class LibraryApplication {
    @Value("${book.management.url}")
    public String bookManagementUrl;

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    @Qualifier("libraryAPI")
    public RestTemplate restTemplate() {
        System.out.println("Initializing RestTemplate with URL: " + bookManagementUrl);
        return new RestTemplateBuilder()
                .rootUri(bookManagementUrl)
                .build();
    }
}