package ru.edu.springdata.repository;

import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookDto;

import java.util.List;

public interface BookRepository {

    List<Book> get(Long id);
    List<Book> getAll();
    void save(Book book);
    void update(Book book);
    void delete(Long id);
}
