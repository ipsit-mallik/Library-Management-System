package com.ip.Library_management_system.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ip.Library_management_system.entity.Book;
import com.ip.Library_management_system.entity.User;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	List<Book> findByBorrowedBy(User user);
}
