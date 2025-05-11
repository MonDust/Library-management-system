package org.DTOclasses;

import lombok.Value;
import org.entities.Library;

import java.util.UUID;

@Value
public class ReadLibraryDTO {
    UUID id;
    String name;

    public static ReadLibraryDTO from(Library library) {
        return new ReadLibraryDTO(library.getId(), library.getName());
    }

}
