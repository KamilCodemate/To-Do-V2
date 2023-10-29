package com.kamilcodemate.todoserver;

import com.kamilcodemate.todoserver.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Main Application Class.
 */
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ToDoServerApplication {



    /**
     * Main method to start the application.
     *
     * @param args default arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(ToDoServerApplication.class, args);
    }
}
