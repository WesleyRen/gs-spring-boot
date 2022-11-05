package com.example.book;

import java.util.List;

public interface BookLookupService {
    Book getByIsbn(String isbn);
    List<Book> getAll();
}
