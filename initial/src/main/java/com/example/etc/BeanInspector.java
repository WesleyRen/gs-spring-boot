package com.example.etc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class BeanInspector implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(BeanInspector.class);

  private final Environment env;
  private final ApplicationContext ctx;

  @Autowired
  public BeanInspector(Environment env, ApplicationContext ctx) {
    this.env = env;
    this.ctx = ctx;
  }

  @Override
  public void run(String... args) {
    if (Integer.parseInt(Objects.requireNonNull(env.getProperty("debug.level"))) >= 1) {

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);

      logger.info("Let's inspect the beans provided by Spring Boot:");
      logger.info("Total Spring Bean count: {}.", beanNames.length);

      if (Integer.parseInt(Objects.requireNonNull(env.getProperty("debug.level"))) >= 2) {
        for (String beanName : beanNames) {
          System.out.println(beanName);
        }
      }
    }
  }

}