package ru.edu.springdata.controller;

import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookRequest;

import java.util.List;

public interface BookController {

    Book getBookById(Long id);
    List<Book> getBooks(String language, String category);
    void saveBook(BookRequest request);
    void updateBook(BookRequest request);
    void deleteBook(Long id);
}
