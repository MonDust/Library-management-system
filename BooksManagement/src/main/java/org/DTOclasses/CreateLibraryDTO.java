package org.DTOclasses;

import lombok.Value;
import java.util.UUID;

@Value
public class CreateLibraryDTO {
    UUID id;
    String name;
    String address;
    String city;
}
