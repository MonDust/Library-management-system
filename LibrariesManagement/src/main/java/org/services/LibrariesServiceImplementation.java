package org.services;

import jakarta.transaction.Transactional;
import org.DTOclasses.ReadLibraryDTO;
import org.entities.Library;
import org.DTOclasses.SendLibraryDTO;
import org.repositories.LibrariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LibrariesServiceImplementation implements LibrariesService{
    private final LibrariesRepository librariesRepository;
    @Qualifier("libraryAPI")
    private final RestTemplate restTemplate;
    private final String bookManagementUrl;

    @Autowired
    public LibrariesServiceImplementation(LibrariesRepository librariesRepository, RestTemplate restTemplate, @Value("${book.management.url}") String bookManagementUrl) {
        this.librariesRepository = librariesRepository;
        this.restTemplate = restTemplate;
        this.bookManagementUrl = bookManagementUrl;

        System.out.println("LibrariesRepository: " + librariesRepository);
        System.out.println("RestTemplate: " + restTemplate);
        System.out.println("BookManagementUrl: " + bookManagementUrl);
    }

    @Override
    public void printAllThings() {
        System.out.println("LibrariesRepository: " + librariesRepository);
        System.out.println("RestTemplate: " + restTemplate);
        System.out.println("BookManagementUrl: " + bookManagementUrl);
    }

    @Override
    public List<Library> findAll() {
        return librariesRepository.findAll();
    }

    @Override
    public Optional<Library> findById(UUID id) {
        return librariesRepository.findById(id);
    }

    @Override
    public List<Library> findByName(String name) {
        return librariesRepository.findByName(name);
    }


    @Override
    public void add(Library library) {
        String libraryUrl =  bookManagementUrl + "/libraries/";
        System.out.println("Sending PUT request to: " + libraryUrl);

        SendLibraryDTO updateRequest = SendLibraryDTO.builder().id(library.getId()).build();

        try {
            restTemplate.postForEntity(libraryUrl, updateRequest, ReadLibraryDTO.class);
            System.out.println("PUT request successful.");
        } catch (Exception e) {
            System.err.println("PUT request failed: " + e.getMessage());
            e.printStackTrace();
        }

        librariesRepository.save(library);
    }

    @Override
    public void addNoRequest(Library library) {

        librariesRepository.save(library);
    }

    @Override
    public void update(Library library) {
        librariesRepository.save(library);
    }

    @Override
    public void delete(Library library) {
        restTemplate.delete("/libraries/" + library.getId() + "/");

        librariesRepository.delete(library);
    }

    public void deleteFully(Library library) {
        restTemplate.delete(bookManagementUrl + "/libraries/" + library.getId() + "/books/");

        restTemplate.delete("/libraries/" + library.getId() + "/");

        librariesRepository.delete(library);
    }
}
