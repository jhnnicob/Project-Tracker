package com.projecttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projecttracker.model.Project;
import com.projecttracker.repository.ProjectRepository;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@PostMapping("add")
	public @ResponseBody String add(@RequestBody Project project) {
		projectRepository.save(project);
		return "Save";
	}
	
	@GetMapping("all")
	public @ResponseBody Iterable<Project> getAll() {
		return projectRepository.findAll();
	}
}