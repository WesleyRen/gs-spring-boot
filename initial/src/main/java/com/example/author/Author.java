package com.example.author;

import java.io.Serializable;

public class Author implements Serializable {
  public final static Long serialVersionUID = 1L;

  private String name;
  private Integer numBooksPublished;

  public Author(String isbn, Integer title) {
    this.name = isbn;
    this.numBooksPublished = title;
  }

  public String getName() {
    return name;
  }

  public Author withName(String name) {
    this.name = name;
    return this;
  }

  public Integer getNumBooksPublished() {
    return numBooksPublished;
  }

  public Author withNumBooksPublished(Integer numBooksPublished) {
    this.numBooksPublished = numBooksPublished;
    return this;
  }

  @Override
  public String toString() {
    return "Author{" +
            "name='" + name + '\'' +
            ", numBooksPublished=" + numBooksPublished +
            '}';
  }
}