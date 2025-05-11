package org.DTOclasses;

import lombok.Value;

@Value
public class CreateBookDTO {
    String name;
    int numberOfPages;
}
