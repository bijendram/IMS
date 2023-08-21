package com.ims.categorypolicyservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.categorypolicyservice.Service.PolicyService;
import com.ims.categorypolicyservice.entity.Policy;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
	
	@GetMapping
	public ResponseEntity<List<Policy>> getAllPolicy(){
		List<Policy> allPolicies = policyService.getAllPolicies();
		return new ResponseEntity<>(allPolicies, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy){
		Policy addPolicy = policyService.addPolicy(policy);
		return new ResponseEntity<>(addPolicy, HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/{policyId}")
	public ResponseEntity<Void> deletePolicy(@PathVariable String policyId){
		policyService.deletePolicy(policyId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}
	
	@PutMapping("/{policyId}")
	public ResponseEntity<Policy> updatePolicy(@PathVariable String policyId, @RequestBody Policy policy)
	{
		policy.setPolicyId(policyId);
		Policy updatePolicy = policyService.updatePolicy(policy);
		return new ResponseEntity<>(updatePolicy, HttpStatus.OK);	
	}

}
