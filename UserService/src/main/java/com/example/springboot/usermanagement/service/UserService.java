package com.example.springboot.usermanagement.service;

import com.example.springboot.usermanagement.entity.User;
import java.util.List;

public interface UserService {

	List<User> findAll();

	User findById(String userId);

	User save(User theUser);

	User updateUser(User theUser);

	// void deleteById(String deleteById);

	boolean deleteUser(String userId);

}
