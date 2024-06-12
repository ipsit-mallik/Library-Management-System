package com.ip.Library_management_system.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ip.Library_management_system.entity.Book;
import com.ip.Library_management_system.entity.User;
import com.ip.Library_management_system.exception.BookAlreadyBorrowedException;
import com.ip.Library_management_system.exception.BookNotBorrowedException;
import com.ip.Library_management_system.exception.BookNotFoundException;
import com.ip.Library_management_system.exception.UserNotFoundException;
import com.ip.Library_management_system.repository.BookRepository;
import com.ip.Library_management_system.repository.UserRepository;

@Service
public class BookService {
    @Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book findById(Long id) {
        return bookRepo.findById(id)
        		.orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
    }

    public Book save(Book book) {
        return bookRepo.save(book);
    }
    
    public Book updateBook(Long id, Book book) {
    	Book existingBook = findById(id);
    	updateNonNullProperties(existingBook, book);
    	return bookRepo.save(existingBook);
    }
    
    private void updateNonNullProperties(Book existingBook, Book updatedBook) {
        Field[] fields = Book.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(updatedBook);
                if (value != null) {
                    field.set(existingBook, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error updating book properties", e);
            }
        }
    }

    public Book deleteById(Long bookId) {
    	Book book = findById(bookId);
    	bookRepo.deleteById(bookId);
    	return book;
    }
    
    public Book borrowBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        
        if (book.isBorrowed()) {
            throw new BookAlreadyBorrowedException("Book with ID " + bookId + " is already borrowed.");
        }
            book.setBorrowedBy(user);
            book.setBorrowed(true);
            return save(book);
    }
    
    public Book returnBook(Long bookId) {
    	Optional<Book> bookOpt = bookRepo.findById(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            if (book.isBorrowed()) {
                book.setBorrowedBy(null);
                book.setBorrowed(false);
                return save(book);
            } else {
                throw new BookNotBorrowedException("Book with ID " + bookId + " is not currently borrowed.");
            }
        } else {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }
    }
}
