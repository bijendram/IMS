package com.example.springboot.usermanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.usermanagement.entity.User;
import com.example.springboot.usermanagement.exception.UserErrorResponse;
import com.example.springboot.usermanagement.exception.UserNotFound;
import com.example.springboot.usermanagement.service.UserServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")

public class UserController {
    
	@Autowired
	private UserServiceImpl userService;

//    public UserController(UserService theUserService) {
//        userService = theUserService;
//    }
    
    //CREATE USER
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User tempUser) {
	User saveUser = userService.save(tempUser);
	ResponseEntity.ok(HttpStatus.OK);
	return ResponseEntity.ok(saveUser);
	}
    
    
//    public User createUser(@RequestBody User tempUser) {
//       User theUser = userService.findById(tempUser.getEmail());
//
//        if(theUser == null ){
//            throw new RuntimeException("User already Existed! Consider Updating the user!");
//        }
//
//        User newUser = userService.save(tempUser);
//
//        return newUser;
//
//    }
    
    //GET ALL USERS
    
    // we can't directly call this API from USER Service, we can call only this from Admin Service, it will throw forbidden error.
    //As we defined Scope internal in Okta Security -- API -- Scope --internal
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
	List<User> allUsers = userService.findAll();
	return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

//	public List<User> getAllUsers() {
//	return userService.findAll();
//	}

    
    //GET USER BY ID
   // @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.findById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
//    @GetMapping("/{userId}")
//    public User getUserById(@PathVariable String userId){
//      User tempUser = userService.findById(userId);
//
//        if(tempUser!=null) {
//            throw new UserNotFound("User ID: " + userId + " does not exist!");
//        }
//        return tempUser;
//    }


  //UDPDATE USER
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User theUser) {
    theUser.setUserId(userId);
    User updateUser = userService.updateUser(theUser);
    if (updateUser != null) {
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
        }
    
    
    
    
//    @PutMapping("/{user_id}")
//    public User updateUser(@RequestBody User theUser){
//        if(theUser.getId()== null){
//            throw new UserNotFound("Please provide the ID.");
//        }
//        Optional<User> tempUser = userService.findById(theUser.getId());
//
//        if(tempUser.isEmpty()){
//            throw new UserNotFound("User is not available with id - " + theUser.getId());
//        }
//        User dbUser = userService.save(theUser);
//        return dbUser;
//    }

    //DELETE USER BY ID
    @DeleteMapping("/{userId}")
    
    public ResponseEntity<Void> deleteClaim(@PathVariable String userId) {
    	userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}
    
    
 
//    public String deleteUserById(@PathVariable String userId) {
//        User tempUser = userService.findById(userId);
//
//        if(tempUser!=null){
//            throw new UserNotFound("User is not available with id - " + userId);
//        }
//
//        userService.deleteById(userId);
//        return "Deleted User with the id - " + userId;
//    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFound exc){
        UserErrorResponse error = new UserErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
