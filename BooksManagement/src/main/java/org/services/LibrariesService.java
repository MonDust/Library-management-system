package org.services;

import org.entities.Library;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LibrariesService {

    List<Library> findAll();
    Optional<Library> findById(UUID id);
    void add(Library library);

    void update(Library library);
    void delete(Library library);

    void deleteFully(Library library);
}
