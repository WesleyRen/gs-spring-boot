package com.example.springboot;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = {
		"com.example.*",
})
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.printf("args length: %d, value: %s\n", args.length, Arrays.stream(args).reduce((ret, v) -> ret + ", " + v));
	}

	@Bean
	public Caffeine caffeineConfig() {
		return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
	}

	@Bean
	public CacheManager cacheManager(Caffeine caffeine) {
		CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
		caffeineCacheManager.setCaffeine(caffeine);
		return caffeineCacheManager;
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
