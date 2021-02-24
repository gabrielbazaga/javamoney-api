package com.bazaga.javamoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.bazaga.javamoney.api.config.property.JavamoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(JavamoneyApiProperty.class)
public class JavamoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavamoneyApiApplication.class, args);
	}

}
