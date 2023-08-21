package com.ims.categorypolicyservice.ServiceDao;

import java.util.List;

import com.ims.categorypolicyservice.entity.Policy;

public interface PolicyServiceDao {
	public List<Policy> getAllPolicies();
	public Policy addPolicy(Policy policy);
	public boolean deletePolicy(String policyId);
	public Policy updatePolicy(Policy policy);
	public Policy getPolicyById(String policyId);
}
