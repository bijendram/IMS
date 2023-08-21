package com.example.springboot.usermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Claim {
		
		private String claimId;
		private String policyNumber;
		private String catName;
		private String location;		
		private String dateOfAccident;		
		private String dateOfClaim;		
		private String description;
		private String claimStatus;
		private String catId;
		private String policyId;
		private String userId;
	}

//Claim entity to map the response from the Claim service.