package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.model.Book;

import com.example.repository.BookRepository;


@SpringBootApplication
public class BookApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(BookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(BookRepository bookRepository) {
		return (args) -> {
			bookRepository.save(new Book(1,"AI", 200,3));
			bookRepository.save(new Book(2,"IOT", 100,2));
			bookRepository.save(new Book(3,"AWS", 300,2));
			bookRepository.save(new Book(4,"Kubernates", 100,2));
			logger.info("The sample data has been generated");
		};
	}
}
