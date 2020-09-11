package com.apudasgupta.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails {

	private User user;
	private Collection<Role> roles;

	

	public AppUserDetails(User user, Collection<Role> roles) {
		super();
		this.user = user;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		 return Collections.singleton(new SimpleGrantedAuthority("USER"));
		if (roles == null) {
			System.out.println("==> AppUserDetails:getAuthorities roles null");
			return Collections.emptySet();
		}else {
			System.out.println("==> AppUserDetails:getAuthorities roles not null");
		}
		Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();// HashSet<>();
		roles.forEach(role -> {
			System.out.println("==> role: "+role.getRole());
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		});
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
