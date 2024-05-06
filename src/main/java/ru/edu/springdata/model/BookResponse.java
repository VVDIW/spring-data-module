package ru.edu.springdata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class BookResponse implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("language")
    private String language;

    @JsonProperty("category")
    private String category;

    @JsonProperty("author")
    private String author;

    @JsonProperty("active")
    private boolean active;
}
