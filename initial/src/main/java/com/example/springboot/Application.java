package com.example.springboot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {
		"com.example.caching",
})
@EnableCaching
public class Application {

	public static void main(String[] args) {SpringApplication.run(Application.class, args);}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);

			System.out.println("\nLet's inspect the beans provided by Spring Boot:");
			System.out.printf("Total Spring Bean count: %s.\n", beanNames.length);

			if (args.length > 1 && args[1].equals("true")) {
				for (String beanName : beanNames) {
					System.out.println(beanName);
				}
			}
		};
	}

}
