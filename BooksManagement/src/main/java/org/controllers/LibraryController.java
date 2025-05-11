package org.controllers;

import org.DTOclasses.CreateLibraryDTO;
import org.DTOclasses.ReadLibraryDTO;
import org.DTOclasses.SendLibraryDTO;
import org.DTOclasses.UpdateLibraryDTO;
import org.entities.Library;
import org.services.LibrariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class LibraryController {
    private final LibrariesService librariesService;

    @Autowired
    public LibraryController(LibrariesService librariesService) {
        this.librariesService = librariesService;
    }

    @GetMapping("/libraries/")
    public ResponseEntity<List<ReadLibraryDTO>> getLibraries(){
        List<Library> libraries = librariesService.findAll();

        return new ResponseEntity<>(libraries.stream().map(ReadLibraryDTO::from).toList(), HttpStatus.OK);
    }

    @GetMapping("/libraries/{uuid}/")
    public ResponseEntity<ReadLibraryDTO> getLibrary(@PathVariable UUID uuid){
        Optional<Library> library = librariesService.findById(uuid);

        return library.map(value -> new ResponseEntity<>(ReadLibraryDTO.from(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/libraries/")
    public ResponseEntity<ReadLibraryDTO> createLibrary(@RequestBody CreateLibraryDTO request){
        System.out.println("Received PUT request for UUID: " + request.getId());
        Library library = Library.builder().id(request.getId()).build();

        librariesService.add(library);

        return new ResponseEntity<>(ReadLibraryDTO.from(library), HttpStatus.CREATED);
    }

    @PutMapping("/libraries/{uuid}/")
    public ResponseEntity<ReadLibraryDTO> updateLibrary(@PathVariable UUID uuid, @RequestBody UpdateLibraryDTO request){
        Optional<Library> libraryById = librariesService.findById(uuid);

        if(libraryById.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Library library = libraryById.get();

        librariesService.update(library);

        return new ResponseEntity<>(ReadLibraryDTO.from(library), HttpStatus.OK);
    }

    @DeleteMapping("/libraries/{uuid}/")
    public ResponseEntity<Void> deleteLibrary(@PathVariable UUID uuid){
        Optional<Library> library = librariesService.findById(uuid);

        if(library.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        librariesService.deleteFully(library.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
