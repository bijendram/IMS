package com.ims.categorypolicyservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.categorypolicyservice.ServiceDao.PolicyServiceDao;
import com.ims.categorypolicyservice.entity.Policy;
import com.ims.categorypolicyservice.repo.PolicyRepository;

@Service
public class PolicyService implements PolicyServiceDao {
	
	@Autowired
	private PolicyRepository policyRepo;

	@Override
	public List<Policy> getAllPolicies() {
		
		return policyRepo.findAll();
	}

	@Override
	public Policy addPolicy(Policy policy) {
		Policy findByPolicyNameAndCatName = policyRepo.findByPolicyNameAndCatName(policy.getPolicyName(), policy.getCatName());
		if(findByPolicyNameAndCatName != null) {
			return policy;
		}
		return policyRepo.save(policy);
	}

	@Override
	public boolean deletePolicy(String policyId) {
		Policy findPolicy = policyRepo.findById(policyId).orElse(null);
		if(findPolicy != null) {
			policyRepo.deleteById(policyId);

		}
		
		return false;
	}

	@Override
	public Policy updatePolicy(Policy policy) {
		Policy existingPolicy = policyRepo.findById(policy.getPolicyId()).orElse(null);
		if(existingPolicy != null) {
			existingPolicy.setPolicyName(policy.getPolicyName());
			existingPolicy.setCatName(policy.getCatName());
			existingPolicy.setPricePerMonth(policy.getPricePerMonth());
			return policyRepo.save(existingPolicy);
		}
		
		return null;
	}

	@Override
	public Policy getPolicyById(String policyId) {
		return policyRepo.findById(policyId).orElse(null);
	}

}
