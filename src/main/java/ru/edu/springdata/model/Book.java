package ru.edu.springdata.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Book {

    private Long id;
    private String name;
    private String language;
    private String category; // history, it, health etc...
}
