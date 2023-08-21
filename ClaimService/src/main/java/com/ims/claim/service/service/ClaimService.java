package com.ims.claim.service.service;
import java.util.List;
import com.ims.claim.service.entity.Claim;
public interface ClaimService {

	//Claim operations
	
		//SAVING THE CLAIM
		Claim createClaim(Claim claim);
		
		//RETRIEVING ALL CLAIMS
		List<Claim> viewAllClaim();
		
		//UPDATING CLAIM
		Claim updateClaimById(Claim claim);
				
		//RETRIEVING CLAIM BY CLAIM ID
		//Claim findClaimByClaimId(String claimId);
		Claim  getClaimByClaimId(String claimId);
		
		//DELETING CLAIM BY CLAIM ID
		boolean deleteClaim(String claimId);
			
		
		//GET ALL CLAIM BY CATEGORY NAME
		List<Claim> getAllClaimByCatName(String catName);
			
		//GET CLAIM BY POLICY NUMBER
		List<Claim> getClaimBypolicyNumber(String policyNumber);
		
		//GET ALL CLAIM BY LOCATION
		List<Claim> getClaimByLocation(String location);
		
		
		//GET ALL CLAIM BY POLICY ID
	   List<Claim> getClaimByPolicyId(String policyId);
	    
	    
	  //GET ALL CLAIM BY CATEGORY ID
	    List<Claim> getClaimByCatId(String catId);		
		
	    
	    //GET ALL CLAIM BY USER ID
	    List<Claim> getClaimByUserId(String userId);		
	
}
