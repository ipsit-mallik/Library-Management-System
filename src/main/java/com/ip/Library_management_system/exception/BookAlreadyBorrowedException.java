package com.ip.Library_management_system.exception;

public class BookAlreadyBorrowedException extends RuntimeException{
	public BookAlreadyBorrowedException(String msg) {
		super(msg);
	}
}
