package com.bezkoder.springjwt.security.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.UserStory;
import com.bezkoder.springjwt.repository.ProjectRpository;
import com.bezkoder.springjwt.repository.UserStoryRepository;

@Service
@Transactional
public class ServiceUserStoryImpl implements ServiceUserStory {

	
	 @Autowired
	    private UserStoryRepository userStoryRepository;

	    @Autowired
	    private ProjectRpository projectRepository;

	@Override
	public int save(UserStory userStory, long idProject) {
		Project project = projectRepository.findById(idProject).get();
		userStory.setProject(project);
		UserStory us = userStoryRepository.save(userStory);
		List<UserStory> userStories=project.getUserStories();
        userStories.add(us);
        project.setUserStories(userStories);

        projectRepository.save(project);

        return us.getId();
	}

}
