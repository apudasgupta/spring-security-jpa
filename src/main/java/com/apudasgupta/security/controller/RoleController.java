package com.apudasgupta.security.controller;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apudasgupta.security.auth.RoleService;

/**
 * @author Apu Das Gupta
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("/getList")	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getList(Model model) {
	    model.addAttribute("rows", this.roleService.findAll());
	    return "role-list";
	}
	
	@GetMapping("/create")	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String create(Model model) {	   
	
	    return "role-create";
	}
	
	@PostMapping("/create")	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String createRole(Role role, BindingResult bindingResult,Model model) {	
		
	    return "role-list";
	}
	
}


