//package org.components;
//
//import org.entities.Book;
//import org.entities.Library;
//import org.services.LibrariesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//public class CommandLineRunnerComponent implements CommandLineRunner {
//    private final LibrariesService librariesService;
//    private final RestTemplate restTemplate;
//    Scanner scanner = new Scanner(System.in);
//
//    @Value("${book.management.url}")
//    public String bookManagementUrl;
//
//    @Autowired
//    public CommandLineRunnerComponent(LibrariesService librariesService, RestTemplate restTemplate) {
//        this.librariesService = librariesService;
//        this.restTemplate = restTemplate;
//    }
//
//    private void printMenu() {
//        System.out.println("\nAvailable commands:");
//        System.out.println("list - List everything");
//        System.out.println("list:libraries - List all libraries");
//        System.out.println("find:library - Find library by name");
//        System.out.println("add:library - Add a new library");
//        System.out.println("del:library - Delete an existing library");
//        System.out.println("stop - Stop/exit the application");
//    }
//
//    private void printLibraries() {
//        librariesService.findAll()
//                .stream()
//                .sorted(Comparator.comparing(Library::getName))
//                .forEach(System.out::println);
//    }
//
//    private void printMatchingLibraries(String libraryName) {
//        List<Library> matchingLibraries = librariesService.findByName(libraryName);
//        if(matchingLibraries.isEmpty()) {
//            System.out.println("Library not found");
//            return;
//        }
//
//        System.out.println("Matching libraries: ");
//        librariesService.findByName(libraryName)
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
//        if(librariesService.findByName(libraryName).isEmpty()) return -1;
//
//        System.out.print("Select a library (enter number): ");
//        try {
//            selection = Integer.parseInt(scanner.nextLine().trim());
//            if (selection <= 0 || selection > librariesService.findByName(libraryName).size()) {
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
//    private void addLibrary() {
//        String name, address, city;
//        System.out.print("Enter library name: ");
//        name = scanner.nextLine().trim();
//        System.out.print("Enter library address: ");
//        address = scanner.nextLine().trim();
//        System.out.print("Enter library city: ");
//        city = scanner.nextLine().trim();
//
//        librariesService.add(Library.builder()
//                .name(name)
//                .city(city)
//                .address(address).build());
//        System.out.println("Added library: " + name);
//    }
//
//    public void deleteLibrary() {
//        Library library;
//        if (librariesService.findAll().isEmpty()) {
//            System.out.println("No libraries not found.");
//            return;
//        }
//        System.out.print("Enter library name: ");
//        String name = scanner.nextLine().trim();
//        int selection = findMatchingLibrary(name);
//        if (selection < 0) return;
//        library = librariesService.findByName(name).get(selection);
//
//        String getBookUrl =  bookManagementUrl +  "/libraries/" + library.getId() + "/books/";
//        ResponseEntity<Book[]> response = restTemplate.getForEntity(getBookUrl, Book[].class);
//
//        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().length > 0) {
//            System.out.println("Library is not empty - do you want to delete all books? (y/N)");
//
//            if(scanner.nextLine().trim().equalsIgnoreCase("y")) {
//                librariesService.deleteFully(library);
//                System.out.println("Library deleted: " + library.toText());
//                return;
//            }
//            System.out.println("Library not deleted.");
//        }
//        librariesService.delete(library);
//        System.out.println("Library deleted: " + library);
//        return;
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
//                    printLibraries(); break;
//                case "list:libraries":
//                    printLibraries(); break;
//                case "find:library":
//                    System.out.print("Enter library name: ");
//                    printMatchingLibraries(scanner.nextLine().trim());
//                    break;
//                case "add:library":
//                    addLibrary(); break;
//                case "del:library":
//                    deleteLibrary(); break;
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
