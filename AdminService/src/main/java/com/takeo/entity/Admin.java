package com.takeo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "admin-details")
public class Admin {

	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	private String password;
	private User user;// Creating class user to store User details, all properties copied from USER-SERVICE

	
	
	//@Transient
	// as we will not save into the DB, so we will use @Transient annotation.
	// We'll fetch this data from user micro-service.
	
	//private List<User> user = new ArrayList<>();

}
