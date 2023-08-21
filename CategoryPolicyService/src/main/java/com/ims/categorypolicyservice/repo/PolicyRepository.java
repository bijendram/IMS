package com.ims.categorypolicyservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ims.categorypolicyservice.entity.Policy;

@Repository
public interface PolicyRepository extends MongoRepository<Policy, String> {
	void deleteByCatName(String catName);

	Policy findByPolicyNameAndCatName(String policyName, String catName);



}
