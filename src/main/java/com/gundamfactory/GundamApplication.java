package com.gundamfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GundamApplication {

    public static void main(String[] args) {
        SpringApplication.run(GundamApplication.class, args);
    }

}
