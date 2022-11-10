package com.example.rs.author;

import java.util.Objects;

public class AuthorDto {

    private String name;
    private Integer numBooksPublished;

    public AuthorDto() {}

    public AuthorDto(String name, Integer numBooksPublished) {
        this.name = name;
        this.numBooksPublished = numBooksPublished;
    }

    public String getName() {
        return name;
    }

    public AuthorDto withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNumBooksPublished() {
        return numBooksPublished;
    }

    public AuthorDto withNumBooksPublished(Integer numBooksPublished) {
        this.numBooksPublished = numBooksPublished;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return name.equals(authorDto.name) && numBooksPublished.equals(authorDto.numBooksPublished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numBooksPublished);
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "name='" + name + '\'' +
                ", numBooksPublished=" + numBooksPublished +
                '}';
    }
}
