package com.example.service;

import java.util.List;

import com.example.model.Book;


public interface BookService {
	public List<Book> getAllBook();
	public Book getBookById(long bookId);
	public Book saveBook(Book book);
	public void removeBook(Book book);
}
