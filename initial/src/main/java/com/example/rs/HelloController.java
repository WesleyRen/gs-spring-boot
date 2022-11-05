package com.example.rs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String greeting() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping("/")
	public String moreGreeting() {
		return "Most greetings from Spring Boot!";
	}

}
