package com.cdwintech.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Peter Wu
 */
@EnableScheduling
@EnableCaching
@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
public class TestApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }
}
