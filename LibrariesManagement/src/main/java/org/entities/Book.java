package org.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Comparable<Book>, Serializable {
    @Id
    @ToString.Exclude
    @Builder.Default
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number_of_pages", nullable = false)
    private int numberOfPages;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    protected Book(String name, int numberOfPages, Library library) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.library = library;
    }

    protected Book(String name, int numberOfPages) {
        this.name = name;
        this.numberOfPages = numberOfPages;
    }

    public String toText() {
        return this.name + " " + this.numberOfPages + " | Library: " + library.getId();
    }

    @Override
    public String toString() {
        return this.name + " " + this.numberOfPages + " | Library: " + library.getId() + " | ID: " + this.getId();
    }

    @Override
    public int compareTo(Book obj) {
        return this.toText().compareTo(obj.toText());
    }
}
