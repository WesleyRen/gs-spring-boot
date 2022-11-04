package com.example.book;

public interface BookRepository {

  Book getByIsbn(String isbn);

}