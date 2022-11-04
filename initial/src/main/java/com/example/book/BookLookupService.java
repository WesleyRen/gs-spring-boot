package com.example.book;

import java.util.List;

public interface BookLookupService {
    Book get(String isbn);
    List<Book> getAll();
}
