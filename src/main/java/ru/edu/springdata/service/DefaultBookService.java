package ru.edu.springdata.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookDto;
import ru.edu.springdata.repository.BookRepository;

import java.util.List;
import java.util.function.BiPredicate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;

    private static final BiPredicate<Book, String> isCategoryCorrect = (book, category) -> category == null || book.getCategory().equals(category);
    private static final BiPredicate<Book, String> isLanguageCorrect = (book, language) -> language == null || book.getLanguage().equals(language);

    @Override
    public Book getBookById(Long id) {
        val optionalBook =  bookRepository.get(id).stream().findFirst();
        return optionalBook.orElse(null);
    }

    @Override
    public List<Book> getBooks(String language, String category) {
        return bookRepository.getAll()
                .stream()
                .filter(
                        book -> isCategoryCorrect.test(book, category)
                )
                .filter(
                        book -> isLanguageCorrect.test(book, language)
                )
                .toList();
    }

    @Override
    public void saveOrUpdateBook(BookDto bookDto) {
        if (bookDto.getId() == null) {
            bookRepository.save(
                    Book.builder()
                            .name(bookDto.getName())
                            .language(bookDto.getLanguage())
                            .category(bookDto.getCategory())
                            .build()
            );
        }
        bookRepository.update(
                Book.builder()
                        .id(bookDto.getId())
                        .name(bookDto.getName())
                        .language(bookDto.getLanguage())
                        .category(bookDto.getCategory())
                        .build()
        );
    }


    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }
}
