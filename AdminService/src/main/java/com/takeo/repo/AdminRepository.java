package com.takeo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.takeo.entity.Admin;
import com.takeo.entity.User;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

	List<Admin> getAdminByUsername(Admin admin);

	List<User> findByUser(User user);

	// void deletedeleteUser(String userId);
	// Don't need to make a method for deleting a user by userId from Admin Service,
	// directly implemented a method in Impl class.

	// public Admin verifyUsernameAndPassword(String username, String password);

}
