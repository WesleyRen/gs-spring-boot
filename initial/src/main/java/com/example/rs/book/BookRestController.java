package com.example.rs.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class BookRestController {

	BookLookupWebSvc bookLookupWebSvc;

	@Autowired
	public BookRestController(BookLookupWebSvc bookLookupWebSvc) {
		this.bookLookupWebSvc = bookLookupWebSvc;
	}

	@GetMapping("/book/isbn/{isbn}")
	public BookDto get(@PathVariable String isbn) {
		return bookLookupWebSvc.getByIsbn(isbn);
	}

	@GetMapping("/book/all")
	public Set<BookDto> getAll() {
		return bookLookupWebSvc.getAll();
	}

}
