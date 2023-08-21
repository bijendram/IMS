package com.ims.claim.service.entity;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Claims")
//@Entity, as we're using MongoDB, we don't use @Entity Annotation
public class Claim {

		@Id
		@ApiModelProperty(notes="The unique ID of the claim.")
		private String claimId;
		private String policyNumber;//From Policy Micro-Service
		private String catName;
		private String location;		
		private String dateOfAccident;		
		private String dateOfClaim;		
		@Size(max = 250, message = "Description should be more than 250 words.")
		private String description;
		private String claimStatus;
		private Policy policy;
		
		private String catId;
		private String policyId;
		private String userId;

	}
