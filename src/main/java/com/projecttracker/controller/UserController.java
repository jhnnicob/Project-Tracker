package com.projecttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projecttracker.model.RestResult;
import com.projecttracker.model.User;
import com.projecttracker.repository.UserRepository;
import com.projecttracker.security.JwtUtils;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController extends BaseRestController{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("auth/signin")
	public @ResponseBody String add(@RequestBody User user) {
		userRepository.save(user);
		return "Save";
	}
	
	@GetMapping("all")
	public @ResponseBody Iterable<User> getAll() {
		return userRepository.findAll();
	}
	
	@PostMapping("auth/login")
	
	public @ResponseBody RestResult login(@RequestBody User user) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jws = jwtUtils.generateJwtToken(authentication);
		
		RestResult res = new RestResult();
		res.setSuccess(true);
		
		User loginUser;
		try {
			loginUser = userRepository.findUserByUsername(user.getUsername());
			loginUser.setToken(jws);
			res.setData(loginUser);
		} catch (Exception e) {
			e.printStackTrace();
			res.setData(jws);
		}

		return res;
	}
}
