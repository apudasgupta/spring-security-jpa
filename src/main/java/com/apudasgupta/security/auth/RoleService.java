package com.apudasgupta.security.auth;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Apu Das Gupta
 *
 */

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public Collection<Role> findAll() {
		return this.roleRepository.findAll();
		
	}

	public void save(Role role) {
		this.roleRepository.save(role);
		
	}

	

	public Role getRoleById(Long roleId) {
		return this.roleRepository.findById(roleId).orElse(null);
		
	}

}


