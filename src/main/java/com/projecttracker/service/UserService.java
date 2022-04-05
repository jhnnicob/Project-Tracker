package com.projecttracker.service;

import com.projecttracker.exception.ServiceException;
import com.projecttracker.model.User;

public interface UserService {
	User findByUsername(String username) throws ServiceException;
}
