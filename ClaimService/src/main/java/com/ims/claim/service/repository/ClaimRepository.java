package com.ims.claim.service.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ims.claim.service.entity.Claim;

@Repository
public interface ClaimRepository extends MongoRepository<Claim, String> {

	// To implement any custom method or query, we can write here.
	// For pre-defined method we don't need to.
	// findByID is the pre-defined method that retrieves an entity from the database
	// by its primary key (ID).

	// RETRIEVING POLICY BY CATEGORY NAME
	//List<Claim> getAllClaimByCatName(String catName);

	List<Claim> findByCatName(String catName);
	
	// RETRIEVING POLICY BY POLICY NUMBER
	//List<Claim> getAllClaimByPolicyNumber(String policyNumber);
	List<Claim> findByPolicyNumber(String policyNumber);
	
	// RETRIEVING POLICY BY LOCATION
	//List<Claim> getAllClaimByLocation(String location);
	List<Claim> findByLocation(String location);

	
	List<Claim> findByPolicyId(String policyId);
	
	List<Claim> findByCatId(String catId);
	
	List<Claim> findByUserId(String userId);

	

	// @Query("Select catName from Claims")

}
