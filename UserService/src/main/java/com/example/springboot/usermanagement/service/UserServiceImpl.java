package com.example.springboot.usermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.springboot.usermanagement.entity.Claim;
import com.example.springboot.usermanagement.entity.User;
import com.example.springboot.usermanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	//private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
//    public User findById(String userId) {
//    	
//    User user = userRepository.findById(userId).orElse(null);
//    
//    	http://localhost:8081/claims/64cb1f6feef5113b336c283f
//    	
//    	ArrayList<Claim> forObject = restTemplate.getForObject("http://localhost:8081/claims/user/id/64cc3fe5a346619f5b5861e8", ArrayList.class);
//    	
//    	user.setClaims(forObject);
//    	
//        return user;
//    }

	public User findById(String userId) {

		User user = userRepository.findById(userId).orElse(null);

		// fetch claim of the above user from CLAIM SERVICE
		// http://localhost:8081/claims/user/id/64cc3fe5a346619f5b5861e8

		// ArrayList<Claim> forObject =
		// restTemplate.getForObject("http://localhost:8081/claims/user/id/64cc3fe5a346619f5b5861e8",
		// ArrayList.class);
		// After annotating @LoadBalanced in myconfig Bean class
		// If we change the port no 8081 in claim-service, it will not affect after
		// changing port no of claim service
		ArrayList<Claim> forObject = restTemplate
				.getForObject("http://CLAIM-SERVICE/claims/user/id/64cc3fe5a346619f5b5861e8", ArrayList.class);

		// logger.info("{}",forObject);
		user.setClaim(forObject);

		return user;
	}

	// public Policy getPolicy(String policyId) {
	// return restTemplate.getForObject("http://policy-service/policies/" +
	// policyId, Policy.class);
	// }

	// @Transactional
	@Override
	public User save(User theUser) {

		return userRepository.save(theUser);
	}

//   // @Transactional
//    @Override
//    public void deleteById(String userId) {
//    	
//        userRepository.deleteById(userId);
//    }

	@Override
	public boolean deleteUser(String userId) {
		User deletingUser = userRepository.findById(userId).orElse(null);
		if (deletingUser != null) {
			userRepository.deleteById(userId);
		}
		return false;
	}

	@Override
	public User updateUser(User theUser) {
		Optional<User> existingUser = userRepository.findById(theUser.getUserId());
		User user = existingUser.get();
		if (user != null) {
			user.setFirstname(theUser.getFirstname());
			user.setLastname(theUser.getLastname());
			;
			user.setEmail(theUser.getEmail());
			user.setAddress(theUser.getAddress());
			user.setMarital_status(theUser.getMarital_status());
			user.setPhone_number(theUser.getPassword());
			user.setPassword(theUser.getPassword());
			User updatedUser = userRepository.save(user);
			return updatedUser;
		}
		return null;
	}

}
