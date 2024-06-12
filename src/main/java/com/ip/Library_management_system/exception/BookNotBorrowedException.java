package com.ip.Library_management_system.exception;

public class BookNotBorrowedException extends RuntimeException{
	public BookNotBorrowedException(String msg) {
		super(msg);
	}
}
