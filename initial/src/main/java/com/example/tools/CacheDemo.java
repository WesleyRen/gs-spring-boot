package com.example.tools;

import com.example.book.BookLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CacheDemo implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(CacheDemo.class);

  private final BookLookupService bookLookupService;
  private final Environment env;

  @Autowired
  public CacheDemo(BookLookupService bookLookup, Environment env) {
    this.bookLookupService = bookLookup;
    this.env = env;
  }

  @Override
  public void run(String... args) {
    if (Integer.parseInt(Objects.requireNonNull(env.getProperty("debug.level"))) >= 1) {
      String a = "isbn-1234";
      String b = "isbn-4567";
      List<String> bookList = new ArrayList<>(Arrays.asList(a, b, a, b, a, a));
      bookList.forEach(book -> {
        long startTime = System.nanoTime();
        logger.info("fetching " + book + " ...");
        logger.info("         -> " + bookLookupService.getByIsbn(book).toString() + ". Time (ms): " + (System.nanoTime() - startTime) / 1000000);
      });

      long startTime = System.nanoTime();
      logger.info("fetching all books ...");
      logger.info("         -> " + bookLookupService.getAll().size() + ". Time (ms): " + (System.nanoTime() - startTime) / 1000000);

      startTime = System.nanoTime();
      logger.info("fetching all books again ...");
      logger.info("         -> " + bookLookupService.getAll().size() + ". Time (ms): " + (System.nanoTime() - startTime) / 1000000);
    }
  }

}