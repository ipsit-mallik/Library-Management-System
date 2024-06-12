package com.ip.Library_management_system.exception;

public class BookNotFoundException extends RuntimeException{
	public BookNotFoundException(String msg) {
		super(msg);
	}
}
