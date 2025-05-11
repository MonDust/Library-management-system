package org.components;

import jakarta.annotation.PostConstruct;
import org.entities.Library;
import org.services.LibrariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializerComponent {
    private final LibrariesService librariesService;

    @Autowired
    public DataInitializerComponent(LibrariesService librariesService) {
        this.librariesService = librariesService;
    }

    @PostConstruct
    public void init() {
        Library library1 = Library.builder().id(UUID.fromString("f7c1c395-f737-4fba-bac5-4690003811c0")).name("New").address("New").city("New").build();
        Library library2 = Library.builder().id(UUID.fromString("215cd1ec-1ba4-4f6e-8d0c-17487819b5b0")).name("Old").address("Old").city("Old").build();

        //librariesService.printAllThings();

        librariesService.addNoRequest(library1);
        librariesService.addNoRequest(library2);

        System.out.println("Libraries added");
    }

}
