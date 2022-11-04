package com.example.book;

import java.util.List;

public interface BookRepository {

  Book getByIsbn(String isbn);
  List<Book> getAll();
}