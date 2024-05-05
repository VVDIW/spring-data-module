package ru.edu.springdata.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.edu.springdata.model.BookDto;
import ru.edu.springdata.model.entity.Author;
import ru.edu.springdata.model.entity.Book;
import ru.edu.springdata.model.entity.Category;
import ru.edu.springdata.repository.AuthorRepository;
import ru.edu.springdata.repository.BookRepository;
import ru.edu.springdata.repository.CategoryRepository;

import java.util.List;
import java.util.function.BiPredicate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;

    private static final BiPredicate<Book, String> isCategoryCorrect = (book, category) -> category == null || book.getCategory().getName().equals(category);
    private static final BiPredicate<Book, String> isLanguageCorrect = (book, language) -> language == null || book.getLanguage().equals(language);

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findBookById(id)
                .orElseThrow(
                        () -> new RuntimeException()
                );
    }

    @Override
    @Transactional
    public List<Book> getBooks(String language, String category) {
        return bookRepository.findAll().stream()
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
                            .title(bookDto.getTitle())
                            .title(bookDto.getTitle())
                            .language(bookDto.getLanguage())
                            .category(getCategory(bookDto))
                            .author(getAuthor(bookDto))
                            .active(bookDto.isActive())
                            .build()
            );

            return;
        }

        val optionalBook = bookRepository.findBookById(bookDto.getId());
        if (optionalBook.isEmpty()) {
            throw new RuntimeException();
        }
        val book = optionalBook.get();
        bookRepository.save(
                Book.builder()
                        .id(book.getId())
                        .title(bookDto.getTitle())
                        .language(bookDto.getLanguage())
                        .category(getCategory(bookDto))
                        .author(getAuthor(bookDto))
                        .active(bookDto.isActive())
                        .build()
        );
    }


    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteBookById(id);
    }

    private Category getCategory(BookDto bookDto) {
        return categoryRepository.findByName(bookDto.getCategory())
                .orElseGet(
                        () -> categoryRepository.save(
                                Category.builder()
                                        .name(bookDto.getCategory())
                                        .build()
                        )
                );
    }

    private Author getAuthor(BookDto bookDto) {
        val name = bookDto.getAuthor().split(" ");
        return authorRepository.findAuthorByFirstNameAndLastName(name[1], name[0])
                .orElseGet(
                        () -> authorRepository.save(
                                Author.builder()
                                        .firstName(name[1])
                                        .lastName(name[0])
                                        .build()
                        )
                );
    }
}
