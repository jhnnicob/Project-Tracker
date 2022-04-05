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
import com.projecttracker.util.ExceptionUtil;

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
	
	@PostMapping("/auth/signup")
	public @ResponseBody RestResult add(@RequestBody User user) {
		RestResult restResult = new RestResult();
		try {
			userRepository.save(user);
			restResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			restResult.setData("Somthing went wrong");
		}
		return restResult;
	}
	
	@GetMapping("/all")
	public @ResponseBody RestResult getAll() {
		RestResult restResult = RestResult.negativeInstance();
		try {
			restResult.setData(userRepository.findAll());
			restResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			restResult.setMessage(e.getMessage());
			restResult.setStackTrace(ExceptionUtil.toString(e));
		}
		return restResult;
	}
	
	@PostMapping("/auth/login")
	public @ResponseBody RestResult login(@RequestBody User user) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jws = jwtUtils.generateJwtToken(authentication);
		
		RestResult restResult = new RestResult();
		restResult.setSuccess(true);
		
		User loginUser;
		try {
			loginUser = controllerUtil.getLoginUser();
			loginUser.setToken(jws);
			restResult.setData(loginUser);
		} catch (Exception e) {
			e.printStackTrace();
			restResult.setData(jws);
		}
		return restResult;
	}
}
