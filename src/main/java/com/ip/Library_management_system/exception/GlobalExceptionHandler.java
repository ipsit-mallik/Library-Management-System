package com.ip.Library_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ip.Library_management_system.responsehandler.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseStructure<BookNotFoundException> handleBookNotFound(BookNotFoundException bnf){
		ResponseStructure<BookNotFoundException> res = new ResponseStructure<BookNotFoundException>();
		res.setStatuscode(HttpStatus.NOT_FOUND.value());
		res.setData(null);
		res.setMsg(bnf.getMessage());
		return res;
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseStructure<UserNotFoundException> handleUserNotFound(UserNotFoundException unf){
		ResponseStructure<UserNotFoundException> res = new ResponseStructure<UserNotFoundException>();
		res.setStatuscode(HttpStatus.NOT_FOUND.value());
		res.setData(null);
		res.setMsg(unf.getMessage());
		return res;
	}

	@ExceptionHandler(value = BookAlreadyBorrowedException.class)
	public ResponseStructure<BookAlreadyBorrowedException> handleBookAlreadyBorrowed(BookAlreadyBorrowedException bab){
		ResponseStructure<BookAlreadyBorrowedException> res = new ResponseStructure<BookAlreadyBorrowedException>();
		res.setStatuscode(HttpStatus.BAD_REQUEST.value());
		res.setData(null);
		res.setMsg(bab.getMessage());
		return res;
	}

	@ExceptionHandler(value = BookNotBorrowedException.class)
	public ResponseStructure<BookNotBorrowedException> handleBookNotBorrowed(BookNotBorrowedException bnb){
		ResponseStructure<BookNotBorrowedException> res = new ResponseStructure<BookNotBorrowedException>();
		res.setStatuscode(HttpStatus.BAD_REQUEST.value());
		res.setData(null);
		res.setMsg(bnb.getMessage());
		return res;
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseStructure<Exception> handleException(Exception e){
		ResponseStructure<Exception> res = new ResponseStructure<Exception>();
		res.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		res.setData(null);
		res.setMsg("An error occurred: " + e.getMessage());
		return res;
	}
}

