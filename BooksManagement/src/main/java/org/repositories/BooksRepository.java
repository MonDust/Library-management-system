package org.repositories;

import org.entities.Book;
import org.entities.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BooksRepository extends JpaRepository<Book, UUID> {
    List<Book> findByLibrary(Library library);
    List<Book> findByName(String name);
    List<Book> findByNameAndLibrary(String name, Library library);
    List<Book> findByNumberOfPagesAndName(int numberOfPages, String name);
    void deleteByLibrary(Library library);
}
