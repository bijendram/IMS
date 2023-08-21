package com.takeo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.takeo.entity.Admin;
import com.takeo.entity.User;
import com.takeo.repo.AdminRepository;
import com.takeo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	

	@Override
	public Admin createAdmin(Admin admin) {

		Admin ad=adminRepo.save(admin);
		
		if(ad != null)
			return ad;
		else
		return null;
	}

	@Override
	public Optional<Admin> getAdminById(String id) {

		Optional<Admin> ad=adminRepo.findById(id);
		
		if(ad != null)
			return ad;
		else
		return null;
	}

	@Override
	public List<Admin> getAllAdmins() {

		List<Admin> ad=adminRepo.findAll();
		
		return ad;
	}

	@Override
//	public Admin updateAdmin(Admin admin) {
//		Admin ad=adminRepo.save(admin);
//		
//		if(ad != null)
//			return ad;
//		else
//		return null;
//		}

	public Admin updateAdmin(Admin admin)  {
		Optional<Admin> existingAdmin = adminRepo.findById(admin.getId());
		Admin admin1 = existingAdmin.get();
		if (admin1 != null) {
			admin1.setFirstname(admin.getFirstname());
			admin1.setLastname(admin.getLastname());
			admin1.setEmail(admin.getEmail());
			admin1.setUsername(admin.getUsername());
			admin1.setPassword(admin.getPassword());
			Admin updatedAdmin = adminRepo.save(admin);
			return updatedAdmin;
		}
		return null;
	}
	
	
	@Override
	public boolean deleteAdmin(String id) {
		
		Optional<Admin> admin= adminRepo.findById(id);
		Admin ad = admin.get();
		boolean flag=false;
		
		if(admin != null)
			adminRepo.deleteById(id);
		flag=true;
		
		
		return false;

	}
	
	
	@Override
	public List<Admin> getAdminByUsername(Admin admin) {
		// TODO Auto-generated method stub
		List<Admin> admin1 = adminRepo.getAdminByUsername(admin);
		
		if(admin != null)
			return admin1;
		else
		return null;
	}


/*	
	public boolean verifyUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		Admin admin2= adminRepo.verifyUsernameAndPassword(username, password);
		
		if(admin2 != null && admin2.getPassword().equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
*/
	
// GET ALL USERS OF USER SERVICE FROM ADMIN SERVICE
	
	@Override
	public List<User> getAllUsers() {
		// String userServiceUrl = "http://localhost:8083/users";
		//After annotating @LoadBalanced in myconfig Bean class
		 String userServiceUrl = "http://USER-SERVICE/users";

	        ResponseEntity<List<User>> response = restTemplate.exchange(
	                userServiceUrl,
	                HttpMethod.GET,
	                null,
	                new ParameterizedTypeReference<List<User>>() {}
	        );
	        return response.getBody();
	    }

	//@Override
	/*public void deleteUser(String userId) {

	//http://localhost:8083/users/64cc573406233d584c1c16fc		From User Service
	//	boolean deleteUser = adminRepo.deleteUser(userId);
		
		//  String userServiceUrl = "http://localhost:8083/users/" + userId;
		//After annotating @LoadBalanced in myconfig Bean class
		
		  String userServiceUrl = "http://USER-SERVICE/users/" + userId;
		  
	        restTemplate.delete(userServiceUrl);
	}*/
	

public ResponseEntity<String> deleteUser(String userId) {
	 ResponseEntity<User> userResponse = restTemplate.getForEntity("http://USER-SERVICE/users/" + userId, User.class);

     if (userResponse.getBody() != null) {
         restTemplate.delete("http://USER-SERVICE/users/" + userId);
         return ResponseEntity.ok("User deleted successfully");
     } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
     }
 }


public User getUserDetails(String userId) {
	
	//http://localhost:8083/users/64cd5913e16b55546a0dbfa2		From User Service
    // Construct the URL for the User microservice endpoint
    String userMicroserviceUrl = "http://USER-SERVICE/users/" + userId;

    // Call the User microservice using RestTemplate (or any other client of your choice)
    User user = restTemplate.getForObject(userMicroserviceUrl, User.class);

    return user;
}


}

	
	

