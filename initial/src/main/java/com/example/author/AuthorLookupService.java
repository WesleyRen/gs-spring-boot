package com.example.author;

import java.util.List;

public interface AuthorLookupService {
    Author getByName(String name);
    List<Author> getAll();
}
