package ru.edu.springdata.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Сущность описывающая книги.
 * Между книгами и категориями связь один ко многим.
 * Книга может быть только в одной категории. В одной категории может быть множество книг.
 * <p>
 * Между книгами и авторами связь многие ко многим.
 * Между авторами и адресами свзяь один к одному
 */

@Builder
@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String language;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;


    private boolean active;
}