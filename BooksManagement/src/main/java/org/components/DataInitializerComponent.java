package org.components;

import jakarta.annotation.PostConstruct;
import org.entities.Book;
import org.entities.Library;
import org.repositories.LibrariesRepository;
import org.services.BooksService;
import org.services.BooksServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Stream;

@Component
public class DataInitializerComponent {
    //private final BooksService booksService;
    private final LibrariesRepository libraryRepository;

    @Autowired
    public DataInitializerComponent(LibrariesRepository libraryRepository) {
        //this.booksService = booksService;
        this.libraryRepository = libraryRepository;
    }

    @PostConstruct
    public void init() {
        Library library1 = Library.builder().id(UUID.fromString("f7c1c395-f737-4fba-bac5-4690003811c0")).build();
        Book book1 = Book.autoBuilder().id(UUID.fromString("c97d0bc7-bc16-48b0-8346-54466a53d6c7")).name("Jave").numberOfPages(123).library(library1).build();
        Book book2 = Book.autoBuilder().id(UUID.fromString("ca2b68cc-f889-4116-a6d6-3c7de696c64e")).name("New Book").numberOfPages(101).library(library1).build();

        Library library2 = Library.builder().id(UUID.fromString("215cd1ec-1ba4-4f6e-8d0c-17487819b5b0")).build();
        Book book3 = Book.autoBuilder().id(UUID.fromString("9e18c80d-1ebf-407b-b315-371b02313ac7")).name("Data").numberOfPages(321).library(library2).build();
        Book book4 = Book.autoBuilder().id(UUID.fromString("8b60f244-3a94-4a28-a8f8-89a534dabc77")).name("Old Book").numberOfPages(202).library(library2).build();

        //Stream.of(book1,book2, book3, book4).forEach(booksService::createBook);
        Stream.of(library1, library2).forEach(libraryRepository::save);

        System.out.println("Books added");
    }

}
