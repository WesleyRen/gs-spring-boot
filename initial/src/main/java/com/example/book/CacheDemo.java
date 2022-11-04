package com.example.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CacheDemo implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(CacheDemo.class);

  private final BookRepository bookRepository;

  public CacheDemo(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    String a = "isbn-1234";
    String b = "isbn-4567";
    List<String> bookList = new ArrayList<>(Arrays.asList(a, b, a, b, a, a));
    bookList.forEach(book -> {
      logger.info("fetching " + book + " ...");
      logger.info("         -> " + bookRepository.getByIsbn(book).toString());
    });
  }

}