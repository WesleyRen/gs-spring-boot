package com.example.book;

import java.io.Serializable;

public class Book implements Serializable {

  public final static Long serialVersionUID = 1L;

  private String isbn;
  private String title;

  public Book(String isbn, String title) {
    this.isbn = isbn;
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public Book withIsbn(String isbn) {
    this.isbn = isbn;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Book withTitle(String title) {
    this.title = title;
    return this;
  }

  @Override
  public String toString() {
    return "Book{" +
            "isbn='" + isbn + '\'' +
            ", title='" + title + '\'' +
            '}';
  }
}