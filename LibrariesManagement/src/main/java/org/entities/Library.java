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
public class Library implements Comparable<Library>, Serializable {
    @Id
    @ToString.Exclude
    @Builder.Default
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    public String toText() {
        return this.name + " | " + this.address + " " + this.city;
    }

    @Override
    public String toString() {
        return this.name + " | " + this.address + " " + this.city + "| ID: " + this.id;
    }

    public int hashCodeAdditional() {
        return 7*name.hashCode() + 4*address.hashCode()+ 3*city.hashCode();
    }

    public boolean equals_hash(Object obj){
        if(this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Library library = (Library) obj;
        return library.hashCode() == this.hashCode();
    }

    @Override
    public int compareTo(Library obj){
        return this.toText().compareTo(obj.toText());
    }

    // Just compare all the vars and add the results.
    public int compareVars(Library obj) {
        return name.compareTo(obj.getName()) + address.compareTo(obj.getAddress()) + city.compareTo(obj.getCity());
    }

    public int compareName(Library obj){
        return this.name.compareTo(obj.name);
    }

}




// -------------------------------------------------
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @Builder.Default
//    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Book> books = new ArrayList<>();


// Made to ignore the builder for books list.
//    public static class LibraryBuilder {
//        private LibraryBuilder books(final List<Book> books){
//            return this;
//        }
//    }

//    private void modifyBookList(Book book) {
//        if (this.books instanceof ArrayList) {
//            this.books.add(book);
//        } else {
//            this.books = new ArrayList<>(this.books);
//            this.books.add(book);
//        }
//    }
//
//    public void addBook(String name, int numberOfPages) {
//        Book newBook = Book.builder().name(name).numberOfPages(numberOfPages).library(this).build();
//        modifyBookList(newBook);
//    }
//
//    public void addBook(Book newBook) {
//        newBook.setLibrary(this);
//        modifyBookList(newBook);
//    }

//    public void addAllBooks(ArrayList<Book> books) {
//        for(Book book : books) addBook(book);
//    }

//    private String toTextFull() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Info: ").append(this.toText());
//        sb.append("\nBooks: ");
//        if (books.isEmpty()) {
//            sb.append("No books available.");
//        } else {
//            for (Book book : books) sb.append("\n- ").append(book.toString());
//        }
//        return sb.toString();
//    }
//
//    public void printToTextFull() {
//        System.out.println(toTextFull());
//    }

//    public int compareToTextFull(Library obj) {
//        return this.toTextFull().compareTo(obj.toTextFull());
//    }

//    public void printAllBooks() {
//        System.out.println("Books in library " + this.name + ":");
//        for (Book book : books) {
//            System.out.println(book.toText());
//        }
//    }
