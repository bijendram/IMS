
package com.ims.categorypolicyservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="policies")
public class Policy {
	
	@Id
	private String policyId;
	private String policyName;
	private String catName;
	private int pricePerMonth; 

}
