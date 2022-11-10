package com.example.author.impl;

import com.example.author.Author;
import com.example.author.AuthorLookupService;
import com.example.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultAuthorLookupService implements AuthorLookupService {

    private final AuthorRepository authorRepository;

    @Autowired
    public DefaultAuthorLookupService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
//    @Cacheable(cacheNames = "authors", cacheManager = "memcachedCacheManager")
    public Author getByName(String name) {
        return authorRepository.getByName(name);
    }

    /*
    Need an explicit key for WM driver, without it causes: "Key contains invalid characters:  ``SimpleKey []''".
    SimpleKey.EMPTY is the default key for method without parameters.
     */
    @Override
//    @Cacheable(cacheNames = "authors", cacheManager = "memcachedCacheManager", key = "#root.methodName")
    public List<Author> getAll() {
        return authorRepository.getAll();
    }
}
