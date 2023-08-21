package com.example.springboot.usermanagement.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// as we're not saving it to the DB, we will not create it a entity class.
public class Category {
	
	private String catId;
	private String catName;
	
}
