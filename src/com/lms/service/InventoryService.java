package com.lms.service;

import com.lms.model.Book;
import com.lms.repository.BookRepository;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class InventoryService {
    private final BookRepository bookRepository;

    public InventoryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CRUD
    public void addBook(Book book) { bookRepository.save(book); }

    public void updateBook(Book book) {
        // ensure exists
        bookRepository.findById(book.getIsbn()).orElseThrow(() ->
                new NoSuchElementException("Book not found: " + book.getIsbn()));
        bookRepository.save(book);
    }

    public void removeBook(String isbn) { bookRepository.deleteById(isbn); }

    // Search
    public List<Book> searchByTitle(String title) {
        String q = title.toLowerCase(Locale.ROOT);
        return bookRepository.findAll().stream()
                .filter(b -> b.getTitle().toLowerCase(Locale.ROOT).contains(q))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        String q = author.toLowerCase(Locale.ROOT);
        return bookRepository.findAll().stream()
                .filter(b -> b.getAuthor().toLowerCase(Locale.ROOT).contains(q))
                .collect(Collectors.toList());
    }

    public Book searchByIsbn(String isbn) {
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new NoSuchElementException("Book not found: " + isbn));
    }

    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
}
