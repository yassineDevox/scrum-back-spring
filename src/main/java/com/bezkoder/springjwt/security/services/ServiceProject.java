package com.bezkoder.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;

public interface ServiceProject {
	public Optional<Project> findProject(long id);
	public long addProject(Project project, String username);
	public List<Project> getProjectsByUser(String usename);
	public void addMemberToProject(Project project, User user);
	public void deleteProject(long id);
}
