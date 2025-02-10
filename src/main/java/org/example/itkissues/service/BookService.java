package org.example.itkissues.service;

import org.example.itkissues.model.Book;
import org.example.itkissues.model.Author;
import org.example.itkissues.exception.BookNotFoundException;
import org.example.itkissues.exception.AuthorNotFoundException;
import org.example.itkissues.repository.BookRepository;
import org.example.itkissues.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional
    public Book createBook(Book book) {
        Author author = book.getAuthor();
        if (author.getId() == null) {
            throw new IllegalArgumentException("Author ID must be provided");
        }
        Author existingAuthor = authorRepository.findById(author.getId())
                .orElseThrow(() -> new AuthorNotFoundException(author.getId()));
        book.setAuthor(existingAuthor);
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationYear(bookDetails.getPublicationYear());

        if (bookDetails.getAuthor() != null) {
            Author author = authorRepository.findById(bookDetails.getAuthor().getId())
                    .orElseThrow(() -> new AuthorNotFoundException(bookDetails.getAuthor().getId()));
            book.setAuthor(author);
        }
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}