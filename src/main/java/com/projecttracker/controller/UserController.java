package com.projecttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projecttracker.model.User;
import com.projecttracker.repository.UserRepository;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("add")
	public @ResponseBody String add(@RequestBody User user) {
		userRepository.save(user);
		return "Save";
	}
	
	@GetMapping("all")
	public @ResponseBody Iterable<User> getAll() {
		return userRepository.findAll();
	}
	
}
