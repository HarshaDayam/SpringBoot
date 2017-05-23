package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.services.UserService;


@ComponentScan("com.services")
@RestController 
@RequestMapping(path = "/demo")
								
public class MainController {
	
	
	@Autowired 
	private UserService userService;
	
	@PostMapping(value = "/user")
	public ResponseEntity createUser(@RequestBody User user) {

		userService.createUser(user);

		return new ResponseEntity(user, HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		userService.deleteUser(id);

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/user/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody User user) {

		user = userService.updateUser(user);

		if (null == user) {
			return new ResponseEntity("No user found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity("No user found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}

}
