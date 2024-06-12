package com.ip.Library_management_system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ip.Library_management_system.entity.Book;
import com.ip.Library_management_system.responsehandler.ResponseStructure;
import com.ip.Library_management_system.service.BookService;

@RestController
@RequestMapping("/lms/book")
public class BookController {

	@Autowired
	private BookService service;
	
	@GetMapping
    public ResponseStructure<List<Book>> getAllBooks() {
        ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
        List<Book> books = service.findAll();
        res.setStatuscode(HttpStatus.OK.value());
        res.setData(books);
        res.setMsg("Users retrieved successfully");
        return res;
    }

    @GetMapping("/{id}")
    public ResponseStructure<Book> getBook(@PathVariable Long id) {
        ResponseStructure<Book> res = new ResponseStructure<Book>();
        Book book = service.findById(id);
        res.setStatuscode(HttpStatus.OK.value());
        res.setData(book);
        res.setMsg("Book retrieved successfully");
        return res;
    }

    @PostMapping
    public ResponseStructure<Book> addBook(@RequestBody Book book) {
    	ResponseStructure<Book> res = new ResponseStructure<Book>();
    	Book savedBook = service.save(book);
        res.setStatuscode(HttpStatus.CREATED.value());
        res.setData(savedBook);
        res.setMsg("Book added successfully");
        return res;
    }

    @PutMapping("/{id}")
    public ResponseStructure<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
    	ResponseStructure<Book> res = new ResponseStructure<Book>();
    	Book updatedBook = service.updateBook(id,book);
        if (updatedBook!=null) {
	    	res.setStatuscode(HttpStatus.ACCEPTED.value()); 		
			res.setData(updatedBook);
			res.setMsg("Book updated successfully");
    	}
    	else {
	    	res.setStatuscode(HttpStatus.NOT_FOUND.value()); 		
			res.setData(null);
			res.setMsg("Book not found with ID: " + id);
    	}
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseStructure<Book> deleteBook(@PathVariable Long id) {
    	ResponseStructure<Book> res = new ResponseStructure<Book>();
    	Book deletedBook = service.deleteById(id);
		res.setStatuscode(HttpStatus.OK.value());
		res.setData(deletedBook);
		res.setMsg("Book deleted successfully");
        return res;
    }
    
    @PostMapping("/borrow/{bookId}/by/{userId}")
    public ResponseStructure<Book> borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
    	ResponseStructure<Book> res = new ResponseStructure<Book>();
    	Book borrowedBook = service.borrowBook(bookId, userId);
        res.setStatuscode(HttpStatus.OK.value());
        res.setData(borrowedBook);
        res.setMsg("Book borrowed successfully");
    	return res;
    }

    @PostMapping("/return/{bookId}")
    public ResponseStructure<Book> returnBook(@PathVariable Long bookId) {
        ResponseStructure<Book> res = new ResponseStructure<Book>();
        Book returnedBook = service.returnBook(bookId);
    	res.setStatuscode(HttpStatus.OK.value());
        res.setData(returnedBook);
        res.setMsg("Book returned successfully");
        return res;
    }
}
