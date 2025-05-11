package org.DTOclasses;

import lombok.*;
import java.util.UUID;

@Data
@Value
@Builder
public class SendLibraryDTO {
    private UUID id;
}