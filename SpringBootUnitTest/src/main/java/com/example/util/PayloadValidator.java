package com.example.util;

import com.example.model.Book;

public class PayloadValidator {
	
	public static boolean validateCreatePayload(Book book){
		if (book.getBookId() > 0){
			return false;
		}
		return true;
	}

}
