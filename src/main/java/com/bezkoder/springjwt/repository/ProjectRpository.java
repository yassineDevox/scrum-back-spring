package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Project;

@Repository
public interface ProjectRpository  extends JpaRepository<Project,Long> {

	List<Project> findByUsersId(Long id);

}
