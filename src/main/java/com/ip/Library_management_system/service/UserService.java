package com.ip.Library_management_system.service;

import java.lang.reflect.Field;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ip.Library_management_system.entity.Book;
import com.ip.Library_management_system.entity.User;
import com.ip.Library_management_system.exception.UserNotFoundException;
import com.ip.Library_management_system.repository.BookRepository;
import com.ip.Library_management_system.repository.UserRepository;

@Service
public class UserService {
    @Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	public List<User> findAll() {
        return userRepo.findAll();
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }
	
	public User updateUser(Long id, User user) {
		User existingUser = findById(id);
        updateNonNullProperties(existingUser, user);
        return userRepo.save(existingUser);
	}
    
	private void updateNonNullProperties(User existingUser, User updatedUser) {
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(updatedUser);
                if (value != null) {
                    field.set(existingUser, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error updating user properties", e);
            }
        }
    }
	
	public User deleteById(Long userId) {
	    User user = findById(userId); 
	    List<Book> borrowedBooks = bookRepo.findByBorrowedBy(user);
	    if (!borrowedBooks.isEmpty()) {
		    for (Book book : borrowedBooks) {
		        book.setBorrowedBy(null);
		        book.setBorrowed(false);
		        bookRepo.save(book);
		    }
	    }
	    userRepo.deleteById(userId);
	    return user;
	}
}
