package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.model.User;
import com.repositories.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {

		return userRepository.findOne(id);
	}

	public Iterable<User> getAllUsers() {

		return userRepository.findAll();
	}

	public User createUser(User user) {

		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	public User updateUser(User user) {

		return userRepository.save(user);
	}

}
