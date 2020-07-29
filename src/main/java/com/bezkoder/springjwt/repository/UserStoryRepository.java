package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.UserStory;
@Repository
public interface UserStoryRepository  extends JpaRepository<UserStory,Integer> {
    List<UserStory> findByProjectId(long id);


}
