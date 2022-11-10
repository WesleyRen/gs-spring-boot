package com.example.rs.book;

import java.util.Objects;

public class BookDto {

    private String isbn;
    private String title;

    public BookDto() {
    }

    public BookDto(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDto withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDto withTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return isbn.equals(bookDto.isbn) && title.equals(bookDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title);
    }
}
