package ru.edu.springdata.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.edu.springdata.mapper.BookMapper;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DefaultBookRepository implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    private final BookMapper bookMapper;

    @Override
    public List<Book> get(Long id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?", new Object[]{id}, bookMapper);
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM Books", bookMapper);
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Books (name, language, category) VALUES(?,?,? )", book.getName(), book.getLanguage(), book.getCategory());
    }

    @Override
    public void update(Book book) {
        jdbcTemplate.update("UPDATE Books SET name=?, language=?, category=? WHERE id=?", book.getName(), book.getLanguage(), book.getCategory(), book.getId());

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
    }
}
