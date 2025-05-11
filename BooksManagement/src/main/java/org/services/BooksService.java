package org.services;

import org.entities.Book;
import org.entities.Library;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BooksService {

    List<Book> findAll();
    Optional<Book> getBookById(UUID id);
    List<Book> findBookByName(String name);
    List<Book> findBookByLibrary(Library library);
    List<Library> findAllLibraries();
    List<Library> findLibrariesByName(String name);
    Optional<Library> findBookLibraryById(UUID id);
    List<Book> findBookByNameAndLibrary(String name, Library library);
    List<Book> findBookByNumberOfPagesAndName(int numberOfPages, String name);
    void createBook(Book book);
    void updateBook(Book book);
    void deleteBook(Book book);

    public void deleteBooksByLibrary(Library library);
}
