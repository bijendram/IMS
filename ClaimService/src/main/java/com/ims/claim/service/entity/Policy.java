
package com.ims.claim.service.entity;

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
