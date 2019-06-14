package com.example.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.BookException;

import com.example.model.Book;
import com.example.model.Response;

import com.example.service.BookService;

import com.example.util.PayloadValidator;

@RestController
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/book", method=RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllBook(){
    	logger.info("Returning all the Book�s");
		return new ResponseEntity<List<Book>>(bookService.getAllBook(), HttpStatus.OK);
	}
	
    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
	public ResponseEntity<Book> getToDoById(@PathVariable("bookId") long bookId) throws BookException{
    	logger.info("Book id to return " + bookId);
    	Book book = bookService.getBookById(bookId);
    	if (book == null || book.getBookId() <= 0){
            throw new BookException("Book doesn´t exist");
    	}
		return new ResponseEntity<Book>(bookService.getBookById(bookId), HttpStatus.OK);
	}

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeBookById(@PathVariable("bookId") long bookId) throws BookException{
    	logger.info("Book id to remove " + bookId);
    	Book book = bookService.getBookById(bookId);
    	if (book == null || book.getBookId() <= 0){
            throw new BookException("Book to delete doesn´t exist");
    	}
		bookService.removeBook(book);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Book has been deleted"), HttpStatus.OK);
	}
    
    @RequestMapping(value = "/book", method = RequestMethod.POST)
   	public ResponseEntity<Book> saveToDo(@RequestBody Book payload) throws BookException{
    	logger.info("Payload to save " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new BookException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<Book>(bookService.saveBook(payload), HttpStatus.OK);
   	}
    
    @RequestMapping(value = "/book", method = RequestMethod.PATCH)
   	public ResponseEntity<Book>  updateToDo(@RequestBody Book payload) throws BookException{
    	logger.info("Payload to update " + payload);
    	Book book = bookService.getBookById(payload.getBookId());
    	if (book == null || book.getBookId() <= 0){
            throw new BookException("Book to update doesn´t exist");
    	}
		return new ResponseEntity<Book>(bookService.saveBook(payload), HttpStatus.OK);
   	}
	
}
