package com.example.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CacheDemo implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(CacheDemo.class);

  private final BookLookupService bookLookupService;

  @Autowired
  public CacheDemo(BookLookupService bookLookup) {
    this.bookLookupService = bookLookup;
  }

  @Override
  public void run(String... args) throws Exception {
    String a = "isbn-1234";
    String b = "isbn-4567";
    List<String> bookList = new ArrayList<>(Arrays.asList(a, b, a, b, a, a));
    bookList.forEach(book -> {
      long startTime = System.nanoTime();
      logger.info("fetching " + book + " ...");
      logger.info("         -> " + bookLookupService.get(book).toString() + ". Time (ms): " + (System.nanoTime() - startTime) / 1000000);
    });

    long startTime = System.nanoTime();
    logger.info("fetching all books ...");
    logger.info("         -> " + bookLookupService.getAll().size() + ". Time (ms): " + (System.nanoTime() - startTime) / 1000000);

    startTime = System.nanoTime();
    logger.info("fetching all books again ...");
    logger.info("         -> " + bookLookupService.getAll().size() + ". Time (ms): " + (System.nanoTime() - startTime) / 1000000);
  }

}