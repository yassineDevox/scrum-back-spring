package com.bezkoder.springjwt.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Taches {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title ;
	private String content;
	private String state ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Sprint sprint;

	@ManyToOne(fetch = FetchType.EAGER)
	private User owner;

	
	@ManyToOne(fetch = FetchType.EAGER)
	private UserStory userStory;

	
	public Taches(int id, String title, String content, String state) {	
		this.id = id;
		this.title = title;
		this.content = content;
		this.state = state;
	}

	public Taches() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@JsonIgnore
	public UserStory getUserStory() {
		return userStory;
	}

	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}
	
	@JsonIgnore
	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	

}
