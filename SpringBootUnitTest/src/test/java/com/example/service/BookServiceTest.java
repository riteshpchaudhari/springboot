package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.model.Book;

import com.example.repository.BookRepository;


@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
	
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookServiceImpl bookService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllBook(){
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(1,"AI",100,2));
		bookList.add(new Book(2,"IOT",200,3));
		bookList.add(new Book(3,"BlockChain",200,2));
		when(bookRepository.findAll()).thenReturn(bookList);
		
		List<Book> result = bookService.getAllBook();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetBookById(){
		Book book = new Book(1,"AWS",100,2);
		when(bookRepository.findOne(1L)).thenReturn(book);
		Book result = bookService.getBookById(1);
		assertEquals(1, result.getBookId());
		assertEquals("AWS", result.getTitle());
		assertEquals(100, result.getPrice());
	}
	
	@Test
	public void saveBook(){
		Book book = new Book(8,"Jenkin",100,3);
		when(bookRepository.save(book)).thenReturn(book);
		Book result = bookService.saveBook(book);
		assertEquals(8, result.getBookId());
		assertEquals("Jenkin", result.getTitle());
		assertEquals(100, result.getPrice());
	}
	
	@Test
	public void removeToDo(){
		Book book = new Book(8,"Jenkin",100,3);
		bookService.removeBook(book);
        verify(bookRepository, times(1)).delete(book);
	}
	
	

}

