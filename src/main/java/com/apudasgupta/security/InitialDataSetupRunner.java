package com.apudasgupta.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.apudasgupta.security.auth.Role;
import com.apudasgupta.security.auth.RoleRepository;
import com.apudasgupta.security.auth.User;
import com.apudasgupta.security.auth.UserRepository;



/**
 * @author Apu Das Gupta
 *
 */
@Component
public class InitialDataSetupRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository authGroupRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(String... args) throws Exception {
	
		
		
		
		Role roleAdmin=new Role("ROLE_ADMIN");		
		this.authGroupRepository.save(roleAdmin);
		
		Role roleSysAdmin=new Role("ROLE_SYSTEM_ADMIN");
		this.authGroupRepository.save(roleSysAdmin);
		
		this.authGroupRepository.save(new Role("ROLE_MAIL_ADMIN"));
		this.authGroupRepository.save(new Role("ROLE_GUEST"));
		
		
		User user = new User();
		user.setUsername("admin");
		user.setPassword(bCryptPasswordEncoder.encode("admin"));
//		Set<AuthRole> roles=new HashSet<>();
//		roles.add(roleAdmin);
		user.setRoles(Arrays.asList(roleAdmin,roleSysAdmin));
		this.createUserIfNotFound(user);
		
		System.out.println("showing all users:--");
		userRepository.findAll().forEach((u) -> {
			System.out.println(u.toString());
		});
		

	}
	private User createUserIfNotFound(User user) {
		User findByUsername = this.userRepository.findByUsername(user.getUsername());
		if(findByUsername==null) {
			System.out.println("==> user does not exist - creating: "+user.toString());
			findByUsername=this.userRepository.save(user);
		}else {
			System.out.println("==> user exist: "+user.toString());
		}
		return findByUsername;
	}
	

}
