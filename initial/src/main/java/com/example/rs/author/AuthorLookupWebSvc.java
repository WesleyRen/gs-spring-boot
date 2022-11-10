package com.example.rs.author;

import com.example.author.Author;
import com.example.author.AuthorLookupService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorLookupWebSvc {

    AuthorLookupService authorLookupService;

    public AuthorLookupWebSvc(AuthorLookupService authorLookupService) {
        this.authorLookupService = authorLookupService;
    }

    public AuthorDto getByName(String name) {
        return mapToDto(authorLookupService.getByName(name));
    }

    public Set<AuthorDto> getAll() {
        return authorLookupService.getAll().stream().map(this::mapToDto).collect(Collectors.toSet());
    }

    private AuthorDto mapToDto(Author author) {
        return new AuthorDto().withName(author.getName()).withNumBooksPublished(author.getNumBooksPublished());
    }
}
