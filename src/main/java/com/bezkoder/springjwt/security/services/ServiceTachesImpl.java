package com.bezkoder.springjwt.security.services;

import java.util.ArrayList;
import java.util.List;

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
public class ServiceTachesImpl implements ServiceTaches {
	@Autowired
	private TachesRepository tacheRepository ;
	
	@Autowired
	private SprintRepository sprintRepository ;
	
	@Autowired
	private UserStoryRepository userStoryRepository ;

	@Override
	public List<Taches> getAll() {
		List<Taches> list=new ArrayList<>();
		tacheRepository.findAll().iterator().forEachRemaining(list::add);
      return list;
	}
	
	@Override
	public List<Taches> getTasksBySprint(int idSprint){
		return tacheRepository.findBySprintId(idSprint);
	}

	@Override
	public Taches addTaches(Taches taches, int idSprint) {
		Sprint sprint = sprintRepository.findById(idSprint).get();
		List<Taches> tasks=sprint.getTasks();
		tasks.add(taches);
		sprint.setTasks(tasks);
		sprintRepository.save(sprint);
		
		taches.setSprint(sprint);
		taches=tacheRepository.save(taches);
		return taches;
	}

	@Override
	public void updateTaches(Taches taches) {
		Taches existTache=tacheRepository.findById(taches.getId()).get();
		existTache.setState(taches.getState());
		tacheRepository.saveAndFlush(existTache);
	}

	@Override
	public void add(Taches task, int idUserStory) {
		UserStory userStory= userStoryRepository.findById(idUserStory).get();
		task.setUserStory(userStory);
		Taches t= tacheRepository.save(task);
		List<Taches> tasks=userStory.getTaches();
		tasks.add(t);
		userStory.setTaches(tasks);
		userStoryRepository.save(userStory);		
	}
	@Override
	public List<Taches> getNonTakenTasks(long id){
		List<UserStory> userStories=userStoryRepository.findByProjectId(id);
		List<Taches> tasks=tacheRepository.nonTakenTasks();
		List<Taches> nonTakenTasks=new ArrayList<Taches>();
		userStories.forEach(us->{
			tasks.forEach(task->{
				if(us.getTaches().contains(task) && task.getSprint()==null)
				nonTakenTasks.add(task);
			});
		});

		return nonTakenTasks;
	}

	
}
