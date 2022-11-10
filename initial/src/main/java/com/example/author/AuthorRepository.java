package com.example.author;

import java.util.List;

public interface AuthorRepository {

  Author getByName(String isbn);
  List<Author> getAll();
}