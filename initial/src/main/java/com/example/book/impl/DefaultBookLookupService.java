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
    @Cacheable(cacheNames = "books", cacheManager = "memcachedCacheManager")
    public Book getByIsbn(String isbn) {
        return bookRepository.getByIsbn(isbn);
    }

    /*
    Need an explicit key for WM driver, without it causes: "Key contains invalid characters:  ``SimpleKey []''".
    SimpleKey.EMPTY is the default key for method without parameters.
     */
    @Override
    @Cacheable(cacheNames = "books", cacheManager = "memcachedCacheManager", key = "#root.methodName")
    public List<Book> getAll() {
        return bookRepository.getAll();
    }
}
