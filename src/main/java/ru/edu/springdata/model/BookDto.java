package ru.edu.springdata.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookDto {
    private Long id;

    private String title;

    private String language;

    private String category;

    private String author;


    private boolean active;
}
