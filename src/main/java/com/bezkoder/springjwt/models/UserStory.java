package com.bezkoder.springjwt.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UserStory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy ="userStory")
    private List<Taches> taches=new ArrayList<Taches>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<Taches> getTaches() {
		return taches;
	}
	public void setTaches(List<Taches> taches) {
		this.taches = taches;
	}
	
	
    
    

}
