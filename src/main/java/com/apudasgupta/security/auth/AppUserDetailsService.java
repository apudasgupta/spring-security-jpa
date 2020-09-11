package com.apudasgupta.security.auth;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("==> loadUserByUsername --- :" + username);
		User user = this.userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("cannot find username: " + username);
		} else {
			System.out.println("==> loadUserByUsername user :" + user.toString());
		}
//		Collection<Role> roles = this.roleRepository.findByUserId(user.getId());
//		for (Role role : user.getRoles()) {
//			System.out.println("==> role:"+role.toString());
//		}
		return new AppUserDetails(user,user.getRoles());
	}
}
