package org.services;

import org.entities.Library;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LibrariesService {

    List<Library> findAll();
    Optional<Library> findById(UUID id);
    List<Library> findByName(String name);
    void add(Library library);

    void update(Library library);
    void delete(Library library);

    void deleteFully(Library library);

    void printAllThings();

    void addNoRequest(Library library);

}
