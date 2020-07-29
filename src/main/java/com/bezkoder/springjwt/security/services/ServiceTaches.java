package com.bezkoder.springjwt.security.services;

import java.util.List;

import com.bezkoder.springjwt.models.Taches;

public interface ServiceTaches {
	public List<Taches> getAll();
	public Taches addTaches(Taches taches, int idSprint);
	public void updateTaches( Taches taches);
	public void add(Taches task, int idUserStory);
	public List<Taches> getTasksBySprint(int idSprint);
	public List<Taches> getNonTakenTasks(long id);



}
