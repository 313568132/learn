package com.leon;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication包含以下注解
 *  @SpringBootConfiguration
 *  @EnableAutoConfiguration
 *  @ComponentScan
 */
@SpringBootApplication
@EnableApolloConfig
public class WebApp {
    public static void main(String[] args) {
        SpringApplication.run(WebApp.class);
    }
}
