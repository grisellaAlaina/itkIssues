package org.example.itkissues.repository;

import org.example.itkissues.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    Book update(Book book);
    void deleteById(Long id);
}