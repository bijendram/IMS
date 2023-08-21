package com.ims.claim.service.service;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ims.claim.service.entity.Claim;
import com.ims.claim.service.repository.ClaimRepository;

@Service
public class ClaimServiceImpl implements ClaimService {
	// Storing the data to the database from the service layer.
	// For this we required Repository methods to do CRUD operations.
	
	private static final Logger logger = Logger.getLogger(ClaimServiceImpl.class.getName());
	
	@Autowired
	private ClaimRepository claimRepository;

	@Override
	public Claim createClaim(Claim claim) {

		return claimRepository.save(claim);
	}

	@Override
	public List<Claim> viewAllClaim() {
		List<Claim> findAllClaim = claimRepository.findAll();

		return findAllClaim;
	}

//	@Override
//public Claim updateClaimById(Claim claim) {
//		
//logger.info("Received request to update claim: " + claim.getClaimId());
//// ... rest of the code ...
//Optional<Claim> existingClaim = claimRepository.findById(claim.getClaimId());
//Claim claim2 = existingClaim.get();
//if (claim2 != null) {
// // ... update the claim ...
//	claim2.setPolicyNumber(claim.getPolicyNumber());
//	claim2.setCatName(claim.getCatName());
//	claim2.setLocation(claim.getLocation());
//	claim2.setDateOfAccident(claim.getDateOfAccident());
//	claim2.setDateOfClaim(claim.getDateOfClaim());
//	claim2.setDescription(claim.getDescription());
//	claim2.setClaimStatus(claim.getClaimStatus());
//logger.info("Claim updated successfully: " + claim.getClaimId());
//return claimRepository.save(claim2);
//}
//logger.warning("Claim not found for ID: " + claim.getClaimId());
//return null;
//	}
	
	public Claim updateClaimById(Claim claim) {
		logger.info("Received request to update claim: " + claim.getClaimId());
		Optional<Claim> existingClaim = claimRepository.findById(claim.getClaimId());
		Claim claim2 = existingClaim.get();
		if (claim2 != null) {
			claim2.setPolicyNumber(claim.getPolicyNumber());
			claim2.setCatName(claim.getCatName());
			claim2.setLocation(claim.getLocation());
			claim2.setDateOfAccident(claim.getDateOfAccident());
			claim2.setDateOfClaim(claim.getDateOfClaim());
			claim2.setDescription(claim.getDescription());
			claim2.setClaimStatus(claim.getClaimStatus());
			claim2.setCatId(claim.getCatId());
			claim2.setPolicyId(claim.getPolicyId());
			Claim updatedClaim = claimRepository.save(claim2);
			logger.info("Claim updated successfully: " + claim.getClaimId());
			return updatedClaim;
		}
		logger.warning("Claim not found for ID: " + claim.getClaimId());
		return null;
	}

//This also works.
//	@Override
//    public Claim updateClaimById(Claim claim) {
//
//        Claim existingClaim =claimRepository.findById(claim.getClaimId()).get();
//        existingClaim.setPolicyNumber(claim.getPolicyNumber());
//        existingClaim.setCatName(claim.getCatName());
//        existingClaim.setLocation(claim.getLocation());
//        existingClaim.setDateOfAccident(claim.getDateOfAccident());
//        existingClaim.setDateOfClaim(claim.getDateOfClaim());
//        existingClaim.setDescription(claim.getDescription());
//        existingClaim.setClaimStatus(claim.getClaimStatus());
//        Claim updatedClaim = claimRepository.save(existingClaim);
//        return updatedClaim;
//    }

	
	
//			Optional<Claim> update = claimRepository.findById(claim.getClaimID());//here we're not sending any ID, what ID we get that we'll update.


	@Override
	public Claim getClaimByClaimId(String claimId){

//		// findById return type is Optional
//		// If an entity with the given id exists in the database, findById(ID) will
//		// return an Optional containing that entity.
//		// If no entity with the given id is found, findById(ID) will return an empty
//		// Optional.
//		Optional<Claim> findByClaimId = claimRepository.findById(claimId);
//		Claim claim = findByClaimId.get();
//		return claim;
		
	        return claimRepository.findById(claimId).orElse(null);
	    }
		


	@Override
	public boolean deleteClaim(String claimId) {
        Claim deletingClaim = claimRepository.findById(claimId).orElse(null);
        if (deletingClaim != null) {
            claimRepository.deleteById(claimId);
        }
        return false;
    }
	
	
	@Override
	public List<Claim> getAllClaimByCatName(String catName) {
		// Customized method created in Repository

		List<Claim> allClaimByCatName = claimRepository.findByCatName(catName);

		return allClaimByCatName;
		
			}
// It also works
//	@Override
//	public List<Claim> findAllClaimByCatName(String catName) {
//		// Customized method created in Repository
	
//		return claimRepository.getAllClaimByCatName(catName);
//			}

	@Override
	public List<Claim> getClaimBypolicyNumber(String policyNumber) {
		// Customized method created in Repository

		return claimRepository.findByPolicyNumber(policyNumber);
	}

	// Customized method created in Repository

	@Override
	public List<Claim> getClaimByLocation(String location) {

		return claimRepository.findByLocation(location);
	}

	
	@Override
	public List<Claim> getClaimByPolicyId(String policyId) {

		
		List<Claim> findByPolicyId = claimRepository.findByPolicyId(policyId);
		
		
		return findByPolicyId;
	}

	@Override
	public List<Claim> getClaimByCatId(String catId) {

		
		return claimRepository.findByCatId(catId);
	}

	@Override
	public List<Claim> getClaimByUserId(String userId) {

		
		return claimRepository.findByUserId(userId);
	}

	
	
}


