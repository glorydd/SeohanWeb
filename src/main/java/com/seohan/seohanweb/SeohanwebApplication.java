package com.seohan.seohanweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:account.properties"})
public class SeohanwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeohanwebApplication.class, args);
	}

}
