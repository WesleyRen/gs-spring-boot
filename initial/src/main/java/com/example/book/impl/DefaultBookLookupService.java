package com.example.book.impl;

import com.example.book.Book;
import com.example.book.BookLookupService;
import com.example.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultBookLookupService implements BookLookupService {

    private final BookRepository bookRepository;

    @Autowired
    public DefaultBookLookupService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Cacheable(cacheNames = "books")
    public Book getByIsbn(String isbn) {
        return bookRepository.getByIsbn(isbn);
    }

    @Override
    @Cacheable(cacheNames = "books")
    public List<Book> getAll() {
        return bookRepository.getAll();
    }
}
