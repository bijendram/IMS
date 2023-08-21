package com.takeo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeo.entity.Admin;
import com.takeo.entity.User;
import com.takeo.service.impl.AdminServiceImpl;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminServiceImpl adminService;

	private Logger logger = LoggerFactory.getLogger(AdminController.class);

	@PostMapping
	public ResponseEntity<Admin> register(@Valid @RequestBody Admin admin) {

		Admin msg = adminService.createAdmin(admin);

		return ResponseEntity.status(HttpStatus.CREATED).body(msg);

	}

	@GetMapping
	public ResponseEntity<List<Admin>> getallAdmins() {
		List<Admin> getallAdmin = adminService.getAllAdmins();

		return ResponseEntity.ok(getallAdmin);

	}
	/*
	 * @GetMapping("/viewadmin") public List<Admin> AdminNames(Admin admin) {
	 * List<Admin> viewAdminName= adminService.getAdminByUsername(admin); return
	 * viewAdminName; }
	 */

//	@PutMapping("/update/{id}")
//	public Admin updateAdmininfo(@PathVariable long id, @RequestBody Admin admin)
//	{
//		Admin updateAdmin=adminService.updateAdmin(admin);
//		return updateAdmin;
//	}

	@PutMapping("/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
		admin.setId(id);
		Admin updateAdmin = adminService.updateAdmin(admin);
		if (updateAdmin != null) {
			return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// Retrieve policy, category, claim of the user from ClaimService,
		// CategoryPolicyS9ervice
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable String id) {
		boolean deleteadmin = adminService.deleteAdmin(id);

		if (deleteadmin)
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.notFound().build();
	}

	// GET ALL USERS OF USER SERVICE FROM ADMIN SERVICE

	@GetMapping("/users")
//    public List<User> getAllUsers() {
//		
//		List<User> allUsers = adminService.getAllUsers();
//		
//        return allUsers;
//    }

	public ResponseEntity<List<User>> getAllUser() {

		List<User> allUsers = adminService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}

	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable String userId) {

		return adminService.deleteUser(userId);
	}

	int retryCount = 1; // For Retry module

	@GetMapping("/users/{userId}")
	@RateLimiter(name = "callingUserServiceLimiter", fallbackMethod = "callingUserFallbackLimiter")
	//@Retry(name = "callingUserServiceRetry", fallbackMethod = "callingUserFallbackRetry")
	//@CircuitBreaker(name = "callingUserService", fallbackMethod = "callingUserFallback")
	public ResponseEntity<User> getUserDetails(@PathVariable String userId) {
		//logger.info("Retry count: {}", retryCount);// for Retry count at console
	//	retryCount++;// For Retry

		User user = adminService.getUserDetails(userId);

		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	// Fallback method for RateLimiter
		public ResponseEntity<String> callingUserFallbackLimiter(String userId, Exception e) {
	
			return new ResponseEntity<String>("Unable to view details at this time. Please try again later.", HttpStatus.TOO_MANY_REQUESTS);
		}
	
	// Fallback method for Retry
//	public ResponseEntity<String> callingUserFallbackRetry(String userId, Throwable t) {
//
//		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
//				.body("Unable to view details at this time. Please try again later.");
//	}

//	// Fallback method for CircuitBreaker
//	public ResponseEntity<String> callingUserFallback(String userId, Throwable t) {
//
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//				.body("Unable to view details at this time. Please try again later.");
//	}

}
