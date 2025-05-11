//package org.components;
//
//import org.entities.Book;
//import org.entities.Library;
//import org.services.BooksService;
//import org.services.LibrariesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//public class CommandLineRunnerComponent implements CommandLineRunner {
//    private final BooksService booksService;
//    private final LibrariesService libraryService;
//    Scanner scanner = new Scanner(System.in);
//
//    @Autowired
//    public CommandLineRunnerComponent(BooksService booksService, LibrariesService librariesService) {
//        this.booksService = booksService;
//        this.libraryService = librariesService;
//    }
//
//    private void printMenu() {
//        System.out.println("\nAvailable commands:");
//        System.out.println("list - List everything");
//        System.out.println("list:libraries - List all libraries");
//        System.out.println("list:books - List all books");
//        System.out.println("list:books:libraries - List books in library");
//        System.out.println("add:book - Add a new book");
//        System.out.println("del:book - Delete an existing book");
//        System.out.println("stop - Stop/exit the application");
//    }
//
//    private void printLibraries() {
//        booksService.findAllLibraries()
//                .stream()
//                .sorted(Comparator.comparing(Library::getName))
//                .forEach(System.out::println);
//
//        System.out.println("__________");
//        libraryService.findAll()
//                .stream()
//                .sorted(Comparator.comparing(Library::getName))
//                .forEach(System.out::println);
//
//    }
//
//    private void printAll() {
//        booksService.findAllLibraries()
//                .stream()
//                .sorted(Comparator.comparing(Library::getName))
//                .forEach(library -> {
//                    System.out.println("Library: " + library);
//                    booksService.findBookByLibrary(library)
//                            .stream()
//                            .sorted(Comparator.comparing(Book::getName))
//                            .forEach(book -> System.out.println("- " + book));
//                });
//    }
//
//    private void printBooks() {
//        booksService.findAll()
//                .stream()
//                .sorted(Comparator.comparing(Book::getName))
//                .forEach(System.out::println);
//    }
//
//    private void printMatchingLibraries(String libraryName) {
//        List<Library> matchingLibraries = booksService.findLibrariesByName(libraryName);
//        if(matchingLibraries.isEmpty()) {
//            System.out.println("Library not found");
//            return;
//        }
//
//        System.out.println("Matching libraries: ");
//        booksService.findLibrariesByName(libraryName)
//                        .forEach(library -> {
//                            AtomicInteger counter = new AtomicInteger(1);
//                            System.out.println(counter.getAndIncrement() + ". " + library);
//                        });
//    }
//
//
//    private int findMatchingLibrary(String libraryName) {
//        int selection;
//        printMatchingLibraries(libraryName);
//        if(booksService.findLibrariesByName(libraryName).isEmpty()) return -1;
//
//        System.out.print("Select a library (enter number): ");
//        try {
//            selection = Integer.parseInt(scanner.nextLine().trim());
//            if (selection <= 0 || selection > booksService.findLibrariesByName(libraryName).size()) {
//                System.out.println("Invalid selection");
//                return -1;
//            }
//            selection --;
//        }
//        catch (InputMismatchException e) {
//            System.out.println("Expected integer value for selection.");
//            return -1;
//        }
//
//        return selection;
//    }
//
//    private void printBooksByLibraries() {
//        System.out.print("Enter library name: ");
//        String name = scanner.nextLine().trim();
//        int selection = findMatchingLibrary(name);
//        if(selection < 0) return;
//        Library library = booksService.findLibrariesByName(name).get(selection);
//
//        booksService.findBookByLibrary(library)
//                .stream()
//                .sorted(Comparator.comparing(Book::getName))
//                .forEach(System.out::println);
//    }
//
//    private void addBook() {
//        String name, numberOfPagesString;
//        int numberOfPages;
//
//        System.out.print("Enter book name: ");
//        name = scanner.nextLine().trim();
//        System.out.print("Enter number of pages of the book: ");
//        numberOfPagesString = scanner.nextLine().trim();
//        try {
//            numberOfPages = Integer.parseInt(numberOfPagesString);
//        } catch (InputMismatchException e) {
//            System.out.println("Expected integer value for number of pages.");
//            return;
//        }
//
//        System.out.print("Enter the library name: ");
//        name = scanner.nextLine().trim();
//        int selection = findMatchingLibrary(name);
//        if (selection < 0) return;
//        Library library = booksService.findLibrariesByName(name).get(selection);
//
//        booksService.createBook(Book.builder()
//                .name(name)
//                .numberOfPages(numberOfPages)
//                .library(library).build());
//        System.out.println("Added book: " + name);
//    }
//
//    public List<Book> returnMatchingBooks(String bookName) {
//        List<Book> matchingBooks = booksService.findBookByName(bookName);
//        if(matchingBooks.isEmpty()) {
//            System.out.println("Book not found");
//            return matchingBooks;
//        }
//
//        System.out.println("Matching books: ");
//        for (int i = 0; i < matchingBooks.size(); i++) {
//            System.out.println((i + 1) + ". " + matchingBooks.get(i).toText());
//        }
//        return matchingBooks;
//    }
//
//    public int findMatchingBooks(String bookName) {
//        int selection;
//        List<Book> matchingBooks = returnMatchingBooks(bookName);
//        if(matchingBooks.isEmpty()) return -1;
//
//        System.out.print("Select a book (enter number): ");
//        try {
//            selection = Integer.parseInt(scanner.nextLine().trim());
//            if (selection <= 0 || selection > matchingBooks.size()) {
//                System.out.println("Invalid selection");
//                return -1;
//            }
//            selection --;
//        }
//        catch (InputMismatchException e) {
//            System.out.println("Expected integer value for selection.");
//            return -1;
//        }
//
//        return selection;
//    }
//
//    public void deleteBook() {
//        if (booksService.findAll().isEmpty()) {
//            System.out.println("No books not found.");
//            return;
//        }
//
//        System.out.print("Enter book name: ");
//        String name = scanner.nextLine().trim();
//        int selection = findMatchingBooks(name);
//        if(selection < 0) return;
//        Book book = booksService.findBookByName(name).get(selection);
//
//        booksService.deleteBook(book);
//        System.out.println("Book deleted: " + book.toText());
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        boolean run = true;
//        System.out.println("Welcome to the Books Library Service");
//        printMenu();
//
//        while (run) {
//            System.out.print("Enter command: ");
//            String command = scanner.nextLine().trim();
//
//            switch (command.toLowerCase()) {
//                case "list":
//                    printAll(); break;
//                case "list:libraries":
//                    printLibraries(); break;
//                case "list:books":
//                    printBooks(); break;
//                case "list:books:libraries":
//                    printBooksByLibraries(); break;
//                case "add:book":
//                    addBook(); break;
//                case "del:book":
//                    deleteBook(); break;
//                case "stop":
//                    run = false;
//                    System.out.println("Stopped!");
//                    break;
//                case "help":
//                    printMenu();
//                    break;
//                default:
//                    System.out.println("Invalid command.");
//                    printMenu();
//            }
//        }
//        scanner.close();
//
//    }
//}
