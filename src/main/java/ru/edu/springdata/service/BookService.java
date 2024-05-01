package ru.edu.springdata.service;

import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookDto;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);
    List<Book> getBooks(String language, String category);
    void saveOrUpdateBook(BookDto request);
    void deleteBook(Long id);
}
