package com.bezkoder.springjwt.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Sprint;
import com.bezkoder.springjwt.models.Taches;
import com.bezkoder.springjwt.models.UserStory;
import com.bezkoder.springjwt.repository.SprintRepository;
import com.bezkoder.springjwt.repository.TachesRepository;
import com.bezkoder.springjwt.repository.UserStoryRepository;

@Service
@Transactional
public class ServiceSprintImpl implements ServiceSprint {
    
	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private TachesRepository tacheRepository;

	@Autowired
	private UserStoryRepository userStoryRepository;
	
	@Override
	public int addSprint(Sprint sprint) {
		sprint=sprintRepository.save(sprint);
		return sprint.getId();
	}

	@Override
	public Optional<Sprint> getSprintById(int id) {
		return sprintRepository.findById(id);
		

	}

	@Override
	public void UpdateSprint(int id, Sprint sprint) {
		Sprint ExistSprint= sprintRepository.findById(id).get();
		if(ExistSprint != null) {
			ExistSprint.setName(sprint.getName());
			ExistSprint.setTasks(sprint.getTasks());
			ExistSprint.setCurrent(sprint.isCurrent());
			ExistSprint.setDescription(sprint.getDescription());
			sprintRepository.save(ExistSprint);
		}		
	}

	@Override
	public void deleteSprint(int id) {
		List<Taches> taches = tacheRepository.findTachesBySprintId(id);
		taches.forEach(task -> {
			task.setSprint(null);
			tacheRepository.save(task);
		});
		
		sprintRepository.deleteById(id);
	}		
	

	@Override
	public void addTaskToSprint(int id, int idTask) {
		Sprint sprint=sprintRepository.findById(id).get();
		Taches task= tacheRepository.findById(idTask).get();

		
		List<Taches> tasks= sprint.getTasks();
		if(!(tasks.contains(task))) {
			tasks.add(task);
			task.setSprint(sprint);
			tacheRepository.save(task);
			
			sprint.setTasks(tasks);
			sprintRepository.save(sprint);}		
	}

	@Override
	public List<Sprint> getSprintsByProjetId(Long projectId) {
		List<UserStory> userStories=userStoryRepository.findByProjectId(projectId);
		List<Sprint> sprints= new ArrayList<Sprint>();
		userStories.forEach(ur->{
			List<Taches> tasks=ur.getTaches();
			tasks.forEach(task->{
				if(task.getSprint()!=null && !sprints.contains(task.getSprint()))
				sprints.add(task.getSprint());
			});

		});
		return sprints;
	}

}
