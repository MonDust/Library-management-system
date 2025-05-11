package org.services;

import jakarta.transaction.Transactional;
import org.entities.Book;
import org.entities.Library;
import org.repositories.BooksRepository;
import org.repositories.LibrariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LibrariesServiceImplementation implements LibrariesService{
    private final LibrariesRepository librariesRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public LibrariesServiceImplementation(LibrariesRepository librariesRepository, BooksRepository booksRepository) {
        this.librariesRepository = librariesRepository;
        this.booksRepository = booksRepository;
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
    public void add(Library library) {
        librariesRepository.save(library);
    }

    @Override
    public void update(Library library) {
        librariesRepository.save(library);
    }

    @Override
    public void delete(Library library) {
        librariesRepository.delete(library);
    }

    @Override
    public void deleteFully(Library library) {
        booksRepository.deleteByLibrary(library);
        librariesRepository.delete(library);
    }
}
