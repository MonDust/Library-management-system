package org.DTOclasses;

import lombok.Value;
import org.entities.Book;

import java.util.UUID;

@Value
public class ReadBookDTO {
    UUID id;
    String name;
    int NumberOfPages;
    ReadLibraryDTO library;

    public static ReadBookDTO from(Book book) {
        return new ReadBookDTO(
                book.getId(),
                book.getName(),
                book.getNumberOfPages(),
                new ReadLibraryDTO(book.getLibrary().getId(), book.getLibrary().getName())
        );
    }
}
