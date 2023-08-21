
package com.example.springboot.usermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
	
	private String policyId;
	private String policyName;
	private String catName;
	private int pricePerMonth; 

}
//Policy entity to map the response from the Policy service.