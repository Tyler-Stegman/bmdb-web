package com.bmdb.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.JsonResponse;
import com.bmdb.business.User;
import com.bmdb.db.UserRepository;

@CrossOrigin
@RestController
@RequestMapping(path="/users")
public class UserController  {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path="/login") // Map ONLY GET Requests
	public JsonResponse login(@RequestBody User u) {
		JsonResponse jr = null;
		Optional<User> usr = userRepository.
				findByUserNameAndPassword(u.getUserName(), u.getPassword());
		if (usr.isPresent()) {
			jr = JsonResponse.getInstance(usr.get());
		}
		else {
			jr = JsonResponse.getErrorInstance("Invalid userName/pwd combination.");
		}
		
		return jr;
	}
		
}
