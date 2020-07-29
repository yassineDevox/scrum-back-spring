package com.bezkoder.springjwt.security.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ProjectRpository;
import com.bezkoder.springjwt.repository.UserRepository;

@Service ("projectService")
@Transactional
public class ServiceProjectImpl implements ServiceProject {

	@Autowired 
	private ProjectRpository projectReprository;

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Optional<Project> findProject(long id) {
		return projectReprository.findById(id);
	}
	
	@Override
	public long addProject(Project project, String username) {
		User user =userRepository.findUserByUsername(username);
		Set<User> users=project.getUsers();
		users.add(user);
		project.setUsers(users);
		projectReprository.save(project);
		return project.getId();
	}
	
	
	@Override
	public List<Project> getProjectsByUser(String username) {
		User user =userRepository.findUserByUsername(username);
		if(user !=null)
			return projectReprository.findByUsersId(user.getId());
			else return null;
	}
	
	
	@Override
	public void addMemberToProject(Project project, User user) {
		Set<User> users=project.getUsers();
		users.add(user);
		project.setUsers(users);
		projectReprository.save(project);		
	}
	
	
	@Override
	public void deleteProject(long id) {
		Project project=projectReprository.findById(id).get();
		project.setUserStories(null);
		projectReprository.save(project);
		projectReprository.deleteById(id);
		
	}

}
