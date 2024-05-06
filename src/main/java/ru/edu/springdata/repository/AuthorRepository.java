package ru.edu.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.edu.springdata.model.entity.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from Author a where a.firstName = ?1 and a.lastName = ?2")
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);

    Author save(Author author);
}
