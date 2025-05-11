package org.entities;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

@Entity
@Table(name = "libraries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    @Id
    @ToString.Exclude
    @Builder.Default
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Builder.Default
    @Column(name = "name", nullable = true)
    private String name = "___NoName___";

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    // Made to ignore the builder for books list.
    public static class LibraryBuilder {
        private LibraryBuilder books(final List<Book> books){
            return this;
        }
    }

    @Override
    public String toString() {
        if(this.name == "___NoName___") return "Library_ID: " + this.id;
        return this.name + "| ID: " + this.id;
    }
}