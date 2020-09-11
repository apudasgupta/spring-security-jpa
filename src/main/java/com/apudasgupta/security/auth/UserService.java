package com.apudasgupta.security.auth;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Apu Das Gupta
 *
 */

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	public Collection<User> findAll() {
		return this.userRepository.findAll();
	}

	
	public void save(User user) {
		System.out.println("==> saving user: " + user.toString());
		
		
		this.userRepository.save(user);
	}


	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);

	}

	public User findById(Long userId) {
		return this.userRepository.findById(userId).orElse(null);
	}

	

}
