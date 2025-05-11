package org.repositories;

import org.entities.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LibrariesRepository extends JpaRepository<Library, UUID> {
    List<Library> findByName(String name);
}