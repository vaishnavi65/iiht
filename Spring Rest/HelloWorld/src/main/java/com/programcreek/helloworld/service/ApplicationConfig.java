package com.programcreek.helloworld.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.programcreek.helloworld.model.Book;
import com.programcreek.helloworld.model.Subject;


@Configuration
@ImportResource({"classpath*:beans.xml"})
public class ApplicationConfig {


	@Bean
	public Book book() {
		return new Book();
	}
	
	@Bean
	public Subject subject() {
		return new Subject();
	}
}
