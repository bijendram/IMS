package com.example.springboot.usermanagement.repository;
import com.example.springboot.usermanagement.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
