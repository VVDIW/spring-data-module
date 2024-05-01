package ru.edu.springdata.controller;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookDto;
import ru.edu.springdata.service.BookService;
import ru.edu.springdata.model.BookRequest;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Controller
public class DefaultBookController implements BookController {


    private final BookService bookService;

    @GetMapping("/book/{id}")
    public Book getBookById(final @PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Override
    @GetMapping("/books")
    public List<Book> getBooks(@Nullable final @RequestParam String language, @Nullable final @RequestParam String category) {
        return bookService.getBooks(language, category);
    }

    @Override
    @PostMapping("/book")
    public void saveBook(final @RequestBody BookRequest request) {
        bookService.saveOrUpdateBook(
                BookDto.builder()
                        .name(request.getName())
                        .language(request.getLanguage())
                        .category(request.getCategory())
                .build()
        );
    }

    @Override
    @PutMapping("/book")
    public void updateBook(final @RequestBody BookRequest request) {
        bookService.saveOrUpdateBook(
                BookDto.builder()
                        .id(request.getId())
                        .name(request.getName())
                        .language(request.getLanguage())
                        .category(request.getCategory())
                        .build()
        );
    }

    @Override
    @DeleteMapping("/book")
    public void deleteBook(@NonNull final @RequestParam Long id) {
        bookService.deleteBook(id);
    }
}
