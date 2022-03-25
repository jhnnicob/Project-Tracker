package com.projecttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecttracker.exception.ServiceException;
import com.projecttracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByUsername(String username) throws ServiceException;
	
}