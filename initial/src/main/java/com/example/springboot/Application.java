package com.example.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {
		"com.example.*",
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.printf("args length: %d, value: %s\n", args.length, Arrays.stream(args).reduce((ret, v) -> ret + ", " + v));
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);

			System.out.println("\nLet's inspect the beans provided by Spring Boot:");
			System.out.printf("Total Spring Bean count: %s.\n", beanNames.length);

			if (args.length > 0 && args[0].equals("true")) {
				for (String beanName : beanNames) {
					System.out.println(beanName);
				}
			}
		};
	}

}
