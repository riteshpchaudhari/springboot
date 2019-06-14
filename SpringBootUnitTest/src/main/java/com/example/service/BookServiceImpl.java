package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;


@Service("BookService")
public class BookServiceImpl implements BookService{

	@Autowired
	private com.example.repository.BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBookById(long bookId) {
		return bookRepository.findOne(bookId);
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void removeBook(Book book) {
		bookRepository.delete(book);
	}
	

}
