package com.kamilcodemate.todoserver;

import com.kamilcodemate.todoserver.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Main Application Class
 */
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ToDoServerApplication {

	/** Main method
	 * @param args default args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ToDoServerApplication.class, args);
	}

}
