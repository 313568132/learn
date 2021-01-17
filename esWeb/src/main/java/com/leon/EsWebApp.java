package com.leon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication包含以下注解
 *  @SpringBootConfiguration
 *  @EnableAutoConfiguration
 *  @ComponentScan
 */
@SpringBootApplication
public class EsWebApp {
    public static void main(String[] args) {
        SpringApplication.run(EsWebApp.class);
    }
}
