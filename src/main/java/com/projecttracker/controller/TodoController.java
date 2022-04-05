package com.projecttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projecttracker.model.RestResult;
import com.projecttracker.model.Todo;
import com.projecttracker.repository.TodoRepository;
import com.projecttracker.util.ExceptionUtil;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/todo")
public class TodoController {
	@Autowired
	private TodoRepository todoRepository;
	
	@PostMapping("add")
	public @ResponseBody RestResult add(@RequestBody Todo todo) {
		RestResult restResult = new RestResult();
		try {
			restResult.setData(todoRepository.save(todo));
			restResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			restResult.setMessage(e.getMessage());
			restResult.setStackTrace(ExceptionUtil.toString(e));
		}
		
		return restResult;
	}
	
	@GetMapping("all")
	public @ResponseBody RestResult getAll() {
		RestResult restResult = RestResult.negativeInstance();
		try {
			restResult.setData(todoRepository.findAll());
			restResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			restResult.setMessage(e.getMessage());
			restResult.setStackTrace(ExceptionUtil.toString(e));
		}
		
		return restResult;
	}
}
