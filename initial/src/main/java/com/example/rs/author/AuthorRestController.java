package com.example.rs.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AuthorRestController {

	AuthorLookupWebSvc authorLookupWebSvc;

	@Autowired
	public AuthorRestController(AuthorLookupWebSvc authorLookupWebSvc) {
		this.authorLookupWebSvc = authorLookupWebSvc;
	}

	@GetMapping("/author/name/{name}")
	public AuthorDto get(@PathVariable String name) {
		return authorLookupWebSvc.getByName(name);
	}

	@GetMapping("/author/all")
	public Set<AuthorDto> getAll() {
		return authorLookupWebSvc.getAll();
	}

}
