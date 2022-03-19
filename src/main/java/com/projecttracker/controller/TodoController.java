package com.projecttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projecttracker.model.Todo;
import com.projecttracker.repository.TodoRepository;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/todo")
public class TodoController {
	@Autowired
	private TodoRepository todoRepository;
	
	@PostMapping("add")
	public @ResponseBody String add(@RequestBody Todo todo) {
		todoRepository.save(todo);
		return "Save";
	}
	
	@GetMapping("all")
	public @ResponseBody Iterable<Todo> getAll() {
		return todoRepository.findAll();
	}
}
