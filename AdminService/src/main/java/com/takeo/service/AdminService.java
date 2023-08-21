package com.takeo.service;

import java.util.List;
import java.util.Optional;

import com.takeo.entity.Admin;
import com.takeo.entity.User;

public interface AdminService {

	public Admin createAdmin(Admin admin);

	public Optional<Admin> getAdminById(String id);

	List<Admin> getAllAdmins();

	public Admin updateAdmin(Admin admin);

	public boolean deleteAdmin(String id);

	List<Admin> getAdminByUsername(Admin admin);
	// public boolean verifyUsernameAndPassword(String username, String password);
	
	List<User> getAllUsers();
	
	//void deleteUser(String userId);

}
