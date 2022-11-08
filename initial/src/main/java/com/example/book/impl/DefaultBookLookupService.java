package com.example.book.impl;

import com.example.book.Book;
import com.example.book.BookLookupService;
import com.example.book.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DefaultBookLookupService implements BookLookupService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultBookLookupService.class);

    private final BookRepository bookRepository;
    private final CacheManager cacheManager;

    private final Environment env;

    @Autowired
    public DefaultBookLookupService(BookRepository bookRepository, CacheManager cacheManager, Environment env) {
        this.bookRepository = bookRepository;
        this.cacheManager = cacheManager;
        this.env = env;
    }

    private void cacheContentCheck(String cacheName){
        if (Objects.equals(env.getProperty("debug.level"), "1")) {
            CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(cacheName);
            assert caffeineCache != null;
            com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            nativeCache.asMap().keySet()
                    .forEach(key -> logger.info("Caffeine cache key: {}, value: {}.", key, nativeCache.asMap().get(key)));
            logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }

    @Override
//    @Cacheable(cacheNames = "books", cacheManager = "alternateCacheManager")
    @Cacheable(cacheNames = "books")
    public Book getByIsbn(String isbn) {
        cacheContentCheck("books");
        return bookRepository.getByIsbn(isbn);
    }

    @Override
    @Cacheable(cacheNames = "books")
    public List<Book> getAll() {
        cacheContentCheck("books");
        return bookRepository.getAll();
    }
}
