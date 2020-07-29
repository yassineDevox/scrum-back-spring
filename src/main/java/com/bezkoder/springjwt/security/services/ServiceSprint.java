package com.bezkoder.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import com.bezkoder.springjwt.models.Sprint;

public interface ServiceSprint {

	public int addSprint(Sprint sprint);
	public Optional<Sprint> getSprintById(int id);
	public void UpdateSprint(int id,Sprint sprint);
	public void deleteSprint(int id);
	public void addTaskToSprint(int id, int idTask);
	public List<Sprint> getSprintsByProjetId(Long projectId);
	
}
