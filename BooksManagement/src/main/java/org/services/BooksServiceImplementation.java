package org.services;

import jakarta.transaction.Transactional;
import org.entities.Book;
import org.entities.Library;
import org.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BooksServiceImplementation implements BooksService {
    private final BooksRepository booksRepository;
    private final RestTemplate restTemplate;
    private final String libraryManagemenrUrl;


    @Autowired
    public BooksServiceImplementation(BooksRepository booksRepository, RestTemplate restTemplate, @Value("${library.management.url}") String libraryManagementUrl) {
        this.booksRepository = booksRepository;
        this.restTemplate = restTemplate;
        this.libraryManagemenrUrl = libraryManagementUrl;
    }

    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(UUID id) {
        return booksRepository.findById(id);
    }

    @Override
    public List<Book> findBookByName(String name) {
        return booksRepository.findByName(name);
    }

    @Override
    public List<Book> findBookByLibrary(Library library) {
        return booksRepository.findByLibrary(library);
    }

    @Override
    public List<Book> findBookByNameAndLibrary(String name, Library library) {
        return booksRepository.findByNameAndLibrary(name, library);
    }

    @Override
    public Optional<Library> findBookLibraryById(UUID id) {
        try {
            String libraryUrl = libraryManagemenrUrl + "/libraries/" + id;
            return Optional.ofNullable(restTemplate.getForObject(libraryUrl, Library.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Library> findAllLibraries() {
        try {
            String libraryUrl = libraryManagemenrUrl + "/libraries/";

            ResponseEntity<List<Library>> response = restTemplate.exchange(
                    libraryUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Library>>() {}
            );
            return response.getBody() != null ? response.getBody() : new ArrayList<>();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Library> findLibrariesByName(String name) {
        List<Library> libraryList_ = findAllLibraries();

        return libraryList_.stream()
                .filter(library -> library.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }


    @Override
    public List<Book> findBookByNumberOfPagesAndName(int numberOfPages, String name) {
        return booksRepository.findByNumberOfPagesAndName(numberOfPages, name);
    }

    @Override
    public void createBook(Book book) {
        booksRepository.save(book);
    }

    @Override
    public void updateBook(Book book) {
        booksRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        booksRepository.delete(book);
    }

    @Override
    public void deleteBooksByLibrary(Library library) {
        booksRepository.deleteByLibrary(library);
    }
}
