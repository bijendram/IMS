package com.ims.claim.service.restController;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
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
import com.ims.claim.service.entity.Claim;
import com.ims.claim.service.service.ClaimServiceImpl;

@RestController
@RequestMapping("/claims")
public class ClaimController {

	
	private static final Logger logger = Logger.getLogger(ClaimController.class.getName());

	
	// from the controller, interacting with service Layer

	@Autowired
	private ClaimServiceImpl claimServiceImpl;

	// SAVING THE CLAIM

	@PostMapping
	//
//	@ApiOperation(value="registering for a claim", notes="It will register the claim", response=Claim.class)
	//
	public ResponseEntity<Claim> registerClaim(@Valid @RequestBody Claim claim) {
		Claim saveClaim1 = claimServiceImpl.createClaim(claim);
		ResponseEntity.ok(HttpStatus.OK);
		return ResponseEntity.ok(saveClaim1);
	}
	
//	@PostMapping
//	public ResponseEntity<String> registerClaim(@Valid @RequestBody Claim claim) {
//		claimServiceImpl.createClaim(claim);
//		return new ResponseEntity<>("Claim registered successfully.", HttpStatus.OK);
//	}
	

	// RETRIEVE ALL CLAIMS

	@GetMapping
	public ResponseEntity<List<Claim>> viewAllClaim() {
		List<Claim> allClaims = claimServiceImpl.viewAllClaim();
		return new ResponseEntity<>(allClaims, HttpStatus.OK);
	}


//	@GetMapping
//	public List<Claim> viewAllClaim() {
//		return claimServiceImpl.viewAllClaim();
//	}

	
	// UPDATE CLAIM

//	@PutMapping("/claims/{claimId}")
//	public ResponseEntity<Claim> updateClaim(@PathVariable String claimId, @RequestBody Claim claim) {
//		claim.setClaimId(claimId);
//		Claim updatedClaim = claimServiceImpl.updateClaimById(claim);
//		if (updatedClaim != null) {
//			return new ResponseEntity<>(updatedClaim, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}


	
@PutMapping("/{claimId}")
public ResponseEntity<Claim> updateClaim(@PathVariable String claimId, @RequestBody Claim claim) {
logger.info("Received PUT request for claimId: " + claimId);
claim.setClaimId(claimId);
 // ... rest of the code ...
Claim updatedClaim = claimServiceImpl.updateClaimById(claim);
if (updatedClaim != null) {
logger.info("Claim updated successfully: " + claimId);
    return new ResponseEntity<>(updatedClaim, HttpStatus.OK);
} else {
logger.warning("Claim not found for ID: " + claimId);
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 }
	}
	
	
//	@PutMapping("/{claimId}")
//    public ResponseEntity<Claim> updateClaim(@PathVariable("claimId") String claimId,
//                                              @RequestBody Claim claim){
//        claim.setClaimId(claimId);
//        Claim updatedClaim = claimServiceImpl.updateClaimById(claim);
//        return new ResponseEntity<>(updatedClaim, HttpStatus.OK);
//    }

	
	
	// RETRIEVE CLAIM BY CLAIM ID

//	@GetMapping("/claims/{claimId}")
//	public ResponseEntity<Claim> getClaimByClaimId(@PathVariable String claimId) {
//		Claim claim = claimServiceImpl.findClaimByClaimId(claimId);
//		return new ResponseEntity<>(claim, HttpStatus.OK);
//	}
	
	@GetMapping("/{claimId}")
    public ResponseEntity<Claim> getClaimById(@PathVariable String claimId) {
        Claim claim = claimServiceImpl.getClaimByClaimId(claimId);
        if (claim != null) {
            return new ResponseEntity<>(claim, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	

	// DELETE CLAIM BY CLAIM ID

	@DeleteMapping("/{claimId}")
    public ResponseEntity<Void> deleteClaim(@PathVariable String claimId) {
       claimServiceImpl.deleteClaim(claimId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}

//	@DeleteMapping("/claims/{claimId}")
//	 public ResponseEntity<String> deleteCourse(@PathVariable String claimId) {
//		 claimServiceImpl.deleteClaim(claimId);
//         return new ResponseEntity<>("Claim ID Deleted Successfully", HttpStatus.OK);
//     }
	

//	@DeleteMapping("/{claimId}")
//    public ResponseEntity<Void> deleteClaim(@PathVariable String claimId) {
//        boolean deleted = claimServiceImpl.deleteClaim(claimId);
//        if (deleted) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }



// RETRIEVE CLAIM BY CATEGORY NAME

//	@GetMapping("/claimbycategory/{catName}")
//	 public ResponseEntity<Claim> getClaimByCatId(@PathVariable String catName) {
//      Claim findAll = claimServiceImpl.findAllClaimByCatName(catName);
//        if (findAll != null) {
//            return new ResponseEntity<>(findAll, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
	
	
	
@GetMapping("/category/name/{catName}")
public ResponseEntity<List<Claim>> viewAllClaimByCatName(@PathVariable String catName) {
List<Claim> findAll = claimServiceImpl.getAllClaimByCatName(catName);
if (findAll != null) {
 return new ResponseEntity<>(findAll, HttpStatus.OK);
} else {
  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}


//RETRIEVE CLAIM BY POLICY NUMBER
@GetMapping("/policy/no/{policyNumber}")
public ResponseEntity<List<Claim>> viewAllClaimByPolicy(@PathVariable String policyNumber) {
List<Claim> findBypolicy = claimServiceImpl.getClaimBypolicyNumber(policyNumber);
if (findBypolicy != null) {
 return new ResponseEntity<>(findBypolicy, HttpStatus.OK);
} else {
  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}



//RETRIEVE CLAIM BY LOCATION
@GetMapping("/policy/location/{location}")
public ResponseEntity<List<Claim>> viewAllClaimByLocation(@PathVariable String location) {
List<Claim> findLocation = claimServiceImpl.getClaimByLocation(location);
if (findLocation != null) {
 return new ResponseEntity<>(findLocation, HttpStatus.OK);
} else {
  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}


//RETRIEVE CLAIM BY POLICY ID
@GetMapping("/policy/id/{policyId}")
public ResponseEntity<List<Claim>> viewAllClaimByPolicyId(@PathVariable String policyId) {
List<Claim> claimByPolicyId = claimServiceImpl.getClaimByPolicyId(policyId);
if (claimByPolicyId != null) {
return new ResponseEntity<>(claimByPolicyId, HttpStatus.OK);
} else {
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}

//RETRIEVE CLAIM BY CATEGORY ID
@GetMapping("/category/id/{catId}")
public ResponseEntity<List<Claim>> viewAllClaimBycatId(@PathVariable String catId) {
List<Claim> claimByCatId = claimServiceImpl.getClaimByCatId(catId);
if (claimByCatId != null) {
return new ResponseEntity<>(claimByCatId, HttpStatus.OK);
} else {
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}


//RETRIEVE CLAIM BY USER ID

@GetMapping("/user/id/{userId}")
public ResponseEntity<List<Claim>> viewAllClaimByuserId(@PathVariable String userId) {
List<Claim> claimByUserId = claimServiceImpl.getClaimByUserId(userId);
if (claimByUserId != null) {
return new ResponseEntity<>(claimByUserId, HttpStatus.OK);
} else {
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}


	}

