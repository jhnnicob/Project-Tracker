package com.projecttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projecttracker.model.Project;
import com.projecttracker.model.RestResult;
import com.projecttracker.repository.ProjectRepository;
import com.projecttracker.util.ExceptionUtil;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@PostMapping("add")
	public @ResponseBody RestResult add(@RequestBody Project project) {
		RestResult restResult = new RestResult();
		try {
			Object data = projectRepository.save(project);
			restResult.setData(data);
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
			Object data = projectRepository.findAll();
			restResult.setData(data);
			restResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			restResult.setMessage(e.getMessage());
			restResult.setStackTrace(ExceptionUtil.toString(e));
		}
		return restResult;
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody RestResult deleteProject(@PathVariable Long id) {
		RestResult restResult = new RestResult();
		try {
			projectRepository.deleteById(id);
			restResult.setSuccess(true);
			restResult.setMessage("Delete project # " + id);
		} catch (Exception e) {
			e.printStackTrace();
			restResult.setMessage(e.getMessage());
			restResult.setStackTrace(ExceptionUtil.toString(e));
		}
		return restResult;
	}
}