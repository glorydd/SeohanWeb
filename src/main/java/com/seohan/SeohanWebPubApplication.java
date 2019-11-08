package com.seohan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableAutoConfiguration 
public class SeohanWebPubApplication extends SpringBootServletInitializer{
	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SeohanWebPubApplication.class); 
	}
	public static void main(String[] args) {
		SpringApplication.run(SeohanWebPubApplication.class, args);
	}
}

