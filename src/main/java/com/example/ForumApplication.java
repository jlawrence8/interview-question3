package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is a sample application that contains REST APIs to support a Forum
 * to create and list out Questions and Replies
 */
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.example.forum"})  // scan Spring Components
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

}
