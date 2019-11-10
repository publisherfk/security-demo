package com.heshi.greendill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@EnableSwagger2
@SpringBootApplication
public class SecurityApp {
	public static void main(String[] args) {
		SpringApplication.run(SecurityApp.class, args);
	}
}
