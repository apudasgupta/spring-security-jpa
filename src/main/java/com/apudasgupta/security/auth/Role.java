package com.apudasgupta.security.auth;

import java.util.Collection;
import java.util.Date;
import java.util.Collection;

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
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Role {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	private String username;

	private String role;

	private Date createdAt;
	
	@ManyToMany(mappedBy = "roles")
    private Collection<User> users;
	
	
//	@ManyToMany
//	@JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
//	private Collection<AuthPrivilege> privileges;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
	
	

	public Role(String role) {
		this.role=role;
	}



	public Role(long id, String role, Date createdAt) {
		super();
		this.id = id;
		this.role = role;
		this.createdAt = createdAt;
	}
	
	
	
	
}
