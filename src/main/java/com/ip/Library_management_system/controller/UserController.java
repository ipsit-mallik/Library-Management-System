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
import com.ip.Library_management_system.entity.User;
import com.ip.Library_management_system.responsehandler.ResponseStructure;
import com.ip.Library_management_system.service.UserService;

@RestController
@RequestMapping("/lms/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping
    public ResponseStructure<List<User>> getAllUsers() {
        ResponseStructure<List<User>> res = new ResponseStructure<List<User>>();
        List<User> users = service.findAll();
        res.setStatuscode(HttpStatus.OK.value());
        res.setData(users);
        res.setMsg("Users retrieved successfully");
        return res;
    }

	@GetMapping("/{id}")
    public ResponseStructure<User> getUser(@PathVariable Long id) {
        ResponseStructure<User> res = new ResponseStructure<User>();
        User user = service.findById(id);
        res.setStatuscode(HttpStatus.OK.value());
        res.setData(user);
        res.setMsg("User retrieved successfully");
        return res;
    }
	
    @PostMapping
    public ResponseStructure<User> addUser(@RequestBody User user) {
    	ResponseStructure<User> res = new ResponseStructure<User>();
    	User savedUser = service.save(user);
        res.setStatuscode(HttpStatus.CREATED.value());
        res.setData(savedUser);
        res.setMsg("User created successfully");
        return res;
    }
    
    @PutMapping("/{id}")
	public ResponseStructure<User> updateUser(@PathVariable Long id, @RequestBody User user)
	{
    	ResponseStructure<User> res = new ResponseStructure<User>();
    	User updatedUser = service.updateUser(id,user);
		if(updatedUser!=null) {
			res.setStatuscode(HttpStatus.ACCEPTED.value()); 		
			res.setData(updatedUser);
			res.setMsg("User updated successfully");    			
		}
		else {
	    	res.setStatuscode(HttpStatus.NOT_FOUND.value()); 		
			res.setData(null);
			res.setMsg("User not found with ID: " + id);
    	}
    	return res;
	}
    
    @DeleteMapping("/{id}")
    public ResponseStructure<User> deleteUser(@PathVariable Long id) {
    	ResponseStructure<User> res = new ResponseStructure<User>();
    	User deletedUser = service.deleteById(id);
		res.setStatuscode(HttpStatus.OK.value());
		res.setData(deletedUser);
		res.setMsg("User deleted successfully");
    	return res;
    }
}
