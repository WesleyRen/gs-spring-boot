package com.example.author;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class SlowAuthorRepositoryMock implements AuthorRepository {

  @Override
  public Author getByName(String name) {
    simulateSlowService();
    return new Author(name, ThreadLocalRandom.current().nextInt(1, 51));
  }

  @Override
  public List<Author> getAll() {
    simulateSlowService();
    return new ArrayList<>(
      Arrays.asList(
              new Author("John Doe", ThreadLocalRandom.current().nextInt(1, 51)),
              new Author("Jean Dill", ThreadLocalRandom.current().nextInt(1, 51)),
              new Author("Jim Smith", ThreadLocalRandom.current().nextInt(1, 51)))
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