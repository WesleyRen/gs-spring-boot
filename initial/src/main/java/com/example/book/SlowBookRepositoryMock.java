package com.example.book;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SlowBookRepositoryMock implements BookRepository {

  @Override
  public Book getByIsbn(String isbn) {
    simulateSlowService();
    return new Book(isbn, "Some book");
  }

  @Override
  public List<Book> getAll() {
    simulateSlowService();
    return new ArrayList<>(
      Arrays.asList(
              new Book("isbn-1000", "Some book"),
              new Book("isbn-2000", "Some book"),
              new Book("isbn-3000", "Some book"))
    );
  }

  // Don't do this at home
  private void simulateSlowService() {
    try {
      long time = 1000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

}