package com.projecttracker.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.projecttracker.exception.ServiceException;
import com.projecttracker.model.User;
import com.projecttracker.repository.UserRepository;

@Component
public class ControllerUtil {

	@Autowired
	private UserRepository userRepository;
	
	public User getLoginUser() throws ServiceException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		return userRepository.findUserByUsername(userDetails.getUsername());
	}
}
