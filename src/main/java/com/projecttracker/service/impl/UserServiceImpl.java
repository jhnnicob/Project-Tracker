package com.projecttracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecttracker.exception.ServiceException;
import com.projecttracker.model.User;
import com.projecttracker.repository.UserRepository;
import com.projecttracker.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private User user;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User findByUsername(String username) throws ServiceException {
		try {
			user = userRepository.findUserByUsername(username);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return user;
	}
}
