package org.controllers;

import org.DTOclasses.CreateBookDTO;
import org.DTOclasses.ReadBookDTO;
import org.DTOclasses.UpdateBookDTO;
import org.entities.Book;
import org.entities.Library;
import org.services.BooksService;
import org.services.LibrariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BookController {
    private final BooksService booksService;
    private final LibrariesService librariesService;

    @Autowired
    public BookController(BooksService booksService, LibrariesService librariesService) {
        this.booksService = booksService;
        this.librariesService = librariesService;
    }

    @GetMapping("/books/")
    public ResponseEntity<List<ReadBookDTO>> getAllBooks() {
        return new ResponseEntity<>(booksService.findAll().stream().map(ReadBookDTO::from).toList(), HttpStatus.OK);
    }

    @GetMapping("/books/{uuid}/")
    public ResponseEntity<ReadBookDTO> getBook(@PathVariable UUID uuid) {
        Optional<Book> book = booksService.getBookById(uuid);

        return book.map(value -> new ResponseEntity<>(ReadBookDTO.from(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/libraries/{uuid}/books/")
    public ResponseEntity<List<ReadBookDTO>> getBooksFromLibrary(@PathVariable UUID uuid) {
        Optional<Library> library = librariesService.findById(uuid);

        if(library.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return library.map(value -> new ResponseEntity<>(booksService.findBookByLibrary(value).stream().map(ReadBookDTO::from).toList(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/libraries/{uuid}/books/")
    public ResponseEntity<ReadBookDTO> createBook(@PathVariable UUID uuid, @RequestBody CreateBookDTO request) {
        Optional<Library> library = librariesService.findById(uuid);

        if(library.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Book newBook = Book.builder()
                .name(request.getName())
                .numberOfPages(request.getNumberOfPages())
                .library(library.get()).build();

        booksService.createBook(newBook);

        return new ResponseEntity<>(ReadBookDTO.from(newBook), HttpStatus.CREATED);
    }

    @PutMapping("/books/{uuid}/")
    public ResponseEntity<ReadBookDTO> updateBook(@PathVariable UUID uuid, @RequestBody UpdateBookDTO request) {
        Optional<Book> bookById = booksService.getBookById(uuid);

        if(bookById.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Book book = bookById.get();

        book.setName(request.getName());
        book.setNumberOfPages(request.getNumberOfPages());
        booksService.updateBook(book);

        return new ResponseEntity<>(ReadBookDTO.from(book), HttpStatus.OK);
    }

    @DeleteMapping("/books/{uuid}/")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID uuid) {
        Optional<Book> book = booksService.getBookById(uuid);

        if(book.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        booksService.deleteBook(book.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/libraries/{uuid}/books/")
    public ResponseEntity<Void> deleteBooksByLibrary(@PathVariable UUID uuid) {
        Optional<Library> library = librariesService.findById(uuid);

        if (library.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        booksService.deleteBooksByLibrary(library.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
