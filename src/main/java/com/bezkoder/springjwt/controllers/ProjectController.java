package com.bezkoder.springjwt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.ServiceProject;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired 
	private ServiceProject projectService;
	
	
	@GetMapping(value="/getproject/{id}")
	public Optional<Project> getProject(@PathVariable(value = "id")int id) {
		return projectService.findProject(id);
	}
	
	@PostMapping(value="/add-project/{username}")
	public long addProject( @RequestBody Project project , @PathVariable String username) {
		return projectService.addProject(project, username);
	}
	
	
	@GetMapping(value="/getAllProjects/{username}")
	public List<Project> getProjects(@PathVariable String username) {
		return projectService.getProjectsByUser(username);
	}

	public void addMemberToProject(Project project, User user) {
		projectService.addMemberToProject(project, user);
	}
	
	
	@GetMapping(value="/delete-project/{id}")
	public void deleteProject(@PathVariable(value = "id") long id) {
		projectService.deleteProject(id);
	}

}
