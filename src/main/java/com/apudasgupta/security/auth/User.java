package com.apudasgupta.security.auth;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
//	@Size(min = 5, message = "Name must be at least 5 characters long")
	@Column(nullable = false, unique = true)

	private String username;
	private String password;
//	@Column(nullable = false, default = true)
	private Boolean enabled=true;
	private Date createdAt;

	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	    @JoinTable( 
	        name = "users_roles", 
	        joinColumns = @JoinColumn(
	          name = "user_id", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "role_id", referencedColumnName = "id")) 
	    private Collection<Role> roles;
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
