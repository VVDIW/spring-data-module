package ru.edu.springdata.controller;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import ru.edu.springdata.model.BookDto;
import ru.edu.springdata.model.BookRequest;
import ru.edu.springdata.model.BookResponse;
import ru.edu.springdata.model.entity.Book;
import ru.edu.springdata.service.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    public BookResponse getBookById(final @PathVariable Long id) {
        val book =  bookService.getBookById(id);
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .language(book.getLanguage())
                .category(book.getCategory().getName())
                .author(book.getAuthor().getLastName() + " " + book.getAuthor().getFirstName())
                .active(book.isActive())
                .build();
    }

    @GetMapping("/books")
    public List<BookResponse> getBooks(@Nullable final @RequestParam String language, @Nullable final @RequestParam String category) {
        val books = new ArrayList<BookResponse>();
        bookService.getBooks(language, category).forEach(
                book -> books.add(
                        BookResponse.builder()
                                .id(book.getId())
                                .title(book.getTitle())
                                .language(book.getLanguage())
                                .category(book.getCategory().getName())
                                .author(book.getAuthor().getLastName() + " " + book.getAuthor().getFirstName())
                                .active(book.isActive())
                        .build()
                )
        );
        return books;
    }

    @PostMapping("/book")
    public void saveBook(final @RequestBody BookRequest request) {
        bookService.saveOrUpdateBook(
                BookDto.builder()
                        .title(request.getTitle())
                        .language(request.getLanguage())
                        .category(request.getCategory())
                        .author(request.getAuthor())
                        .active(request.isActive())
                        .build()
        );
    }

    @PutMapping("/book")
    public void updateBook(final @RequestBody BookRequest request) {
        bookService.saveOrUpdateBook(
                BookDto.builder()
                        .id(request.getId())
                        .title(request.getTitle())
                        .language(request.getLanguage())
                        .category(request.getCategory())
                        .author(request.getAuthor())
                        .active(request.isActive())
                        .build()
        );
    }

    @DeleteMapping("/book")
    public void deleteBook(@NonNull final @RequestParam Long id) {
        bookService.deleteBook(id);
    }
}
