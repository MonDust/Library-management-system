package org.system_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"org.system_gateway.web"})
public class GatewayApplication {
    private final String libraryManagementUrl;
    private final String booksManagementUrl;

    public GatewayApplication(
            @Value("${library.management.url}") String libraryManagementUrl,
            @Value("${book.management.url}") String booksManagementUrl
    ) {
        this.libraryManagementUrl = libraryManagementUrl;
        this.booksManagementUrl = booksManagementUrl;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator storeRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("libraries", route -> route
                        .path(
                                "/libraries/",
                                "/libraries/{uuid}/"
                        )
                        .uri(libraryManagementUrl)
                )
                .route("books", route -> route
                        .path(
                                "/books/",
                                "/books/{uuid}/",
                                "/libraries/{uuid}/books/"
                        )
                        .uri(booksManagementUrl)
                )
                .build();
    }
}
