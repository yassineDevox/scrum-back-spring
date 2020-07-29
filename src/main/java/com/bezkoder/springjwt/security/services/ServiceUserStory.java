package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.UserStory;

public interface ServiceUserStory {
    public int save(UserStory userStory, long idProject);

}
