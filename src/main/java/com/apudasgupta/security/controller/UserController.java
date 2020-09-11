package com.apudasgupta.security.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apudasgupta.security.auth.RoleService;
import com.apudasgupta.security.auth.User;
import com.apudasgupta.security.auth.UserService;

/**
 * @author Apu Das Gupta
 *
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	

	@GetMapping("/profile")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String profile(Model model) {
		
		return "user-profile";
	}

	@PostMapping("/profile")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String profileUpdate( User user, BindingResult bindingResult, Model model) {

		

		return "user-profile";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getList")
	public String getList(Model model) {
		Collection<User> users = this.userService.findAll();
		for (User u : users)
			System.out.println("==> users: " + u.toString());
		model.addAttribute("rows", users);
		return "user-list";
	}

	@GetMapping("/update/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String update(@PathVariable Long userId, Model model) {
		User user = this.userService.findById(userId);
		model.addAttribute("roles", this.roleService.findAll());
		System.out.println("==> user to update: " + user.toString());
		model.addAttribute("user", user);
		return "user-update";
	}

	
	
	@PostMapping("/update/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String updateUser(User user, BindingResult bindingResult, Model model) {
		
		return "user-update";
	}
	
	
	
	@PostMapping("/updatex/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String updateUserx(User user, BindingResult bindingResult, Model model) {
		
		return "user-update";
	}

	@GetMapping("/create")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String create(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", this.roleService.findAll());
		return "user-create";
	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String createUser(User user, BindingResult bindingResult, Model model) {
		
		return "user-list";
	}
}
