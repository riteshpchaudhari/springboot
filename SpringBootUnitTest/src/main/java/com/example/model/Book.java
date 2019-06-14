package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private long bookId;
	private String title;
	private int price;
	private int volumn;

	public Book() {
		super();
	}
	
	
	public long getBookId() {
		return bookId;
	}


	public void setBookId(long bookId) {
		this.bookId = bookId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getVolumn() {
		return volumn;
	}


	public void setVolumn(int volumn) {
		this.volumn = volumn;
	}


	public Book(long bookId, String title, int price,int volumn) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.volumn=volumn;
	}


	public  Book(long bookId, String title) {
		super();
		this.bookId = bookId;
		this.title = title;
	}
		
	
	

}
