package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bezkoder.springjwt.models.Taches;

public interface TachesRepository extends JpaRepository<Taches, Integer>{

	List<Taches> findTachesBySprintId(int id);
	
	List<Taches> findBySprintId(int id);
	@Query("select t from Taches t where t.sprint IS NULL")
	List<Taches> nonTakenTasks();



}
