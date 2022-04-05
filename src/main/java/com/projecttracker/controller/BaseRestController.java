package com.projecttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.projecttracker.util.ControllerUtil;

public class BaseRestController {

	@Autowired
	protected ControllerUtil controllerUtil;
}
