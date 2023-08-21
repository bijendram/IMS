package com.example.springboot.usermanagement.entity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="users")
//@Document("users")
public class User {

    @Id
    private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private String marital_status;
    private String phone_number;
    
    @Transient
  //as we will not save into the DB, so we will use @Transient annotation.
    // We'll fetch this data from respective micro-services.
    private List<Claim> claim = new ArrayList<>();
    
    @Transient
    private List<Category> category = new ArrayList<>();
    
    @Transient
    private List<Policy> policy = new ArrayList<>();
    
    
    
	/*
	 * public User () {}
	 * 
	 * public User(String firstname, String lastname, String email, String address,
	 * String marital_status, String phone_number, String password) { this.firstname
	 * = firstname; this.lastname = lastname; this.email = email; this.address =
	 * address; this.marital_status = marital_status; this.phone_number =
	 * phone_number; this.password = password;
	 * 
	 * }
	 * 
	 * public String getId() { return id; }
	 * 
	 * public void setId(String id) { this.id = id; }
	 * 
	 * public String getFirstname() { return firstname; }
	 * 
	 * public void setFirstname(String firstname) { this.firstname = firstname; }
	 * 
	 * public String getPhone_number() { return phone_number; }
	 * 
	 * public void setPhone_number(String phone_number) { this.phone_number =
	 * phone_number; }
	 * 
	 * public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 * 
	 * public String getLastname() { return lastname; }
	 * 
	 * public void setLastname(String lastname) { this.lastname = lastname; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * public String getAddress() { return address; }
	 * 
	 * public void setAddress(String address) { this.address = address; }
	 * 
	 * public String getMarital_status() { return marital_status; }
	 * 
	 * public void setMarital_status(String marital_status) { this.marital_status =
	 * marital_status; }
	 * 
	 * @Override public String toString() { return "User{" + "id='" + id + '\'' +
	 * ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' +
	 * ", email='" + email + '\'' + ", password='" + password + '\'' + ", address='"
	 * + address + '\'' + ", marital_status='" + marital_status + '\'' +
	 * ", phone_number='" + phone_number + '\'' + '}'; }
	 */
}
