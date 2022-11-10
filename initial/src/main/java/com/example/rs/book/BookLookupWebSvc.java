package com.example.rs.book;

import com.example.book.Book;
import com.example.book.BookLookupService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookLookupWebSvc {

    BookLookupService bookLookupService;

    public BookLookupWebSvc(BookLookupService bookLookupService) {
        this.bookLookupService = bookLookupService;
    }

    public BookDto getByIsbn(String isbn) {
        return mapToDto(bookLookupService.getByIsbn(isbn));
    }

    public Set<BookDto> getAll() {
        return bookLookupService.getAll().stream().map(this::mapToDto).collect(Collectors.toSet());
    }

    private BookDto mapToDto(Book book) {
        return new BookDto().withIsbn(book.getIsbn()).withTitle(book.getTitle());
    }
}
