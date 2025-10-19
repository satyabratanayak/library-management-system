package com.lms.repository;

import com.lms.model.Book;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BookRepository implements Repository<Book, String> {
    private final Map<String, Book> store = new ConcurrentHashMap<>();

    @Override public Optional<Book> findById(String isbn) { return Optional.ofNullable(store.get(isbn)); }
    @Override public void save(Book book) { store.put(book.getIsbn(), book); }
    @Override public void deleteById(String isbn) { store.remove(isbn); }
    @Override public List<Book> findAll() { return new ArrayList<>(store.values()); }
}
