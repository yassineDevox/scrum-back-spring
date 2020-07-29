package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Taches;
import com.bezkoder.springjwt.security.services.ServiceTaches;


@CrossOrigin(origins = "*")
@RequestMapping("/taches")
@RestController
public class TachesController {
	@Autowired 
	private ServiceTaches serviceTaches;
	
	@GetMapping(value="/taches")
	public List<Taches> getAll(){
		return serviceTaches.getAll();
	}
	
	@PostMapping(value="/add-taches/{idSprint}")
	public Taches adTaches(@RequestBody  Taches taches,@PathVariable int idSprint) {
		return serviceTaches.addTaches(taches, idSprint);
	}
	
	@PostMapping(value="/update")
	public void update(@RequestBody Taches taches) {
		serviceTaches.updateTaches(taches);
	}
	@GetMapping(value="/getTasksBySprint/{idSprint}")
	public List<Taches> getTasksBySprint(@PathVariable int idSprint){
		return serviceTaches.getTasksBySprint(idSprint);
	}

	@PostMapping("/add/{idUserStory}")
	public void save(@RequestBody Taches task, @PathVariable int idUserStory){
		serviceTaches.add(task, idUserStory);
	}
	@GetMapping(value="/nonTakenTasks/{idProject}")
	public List<Taches> getNonTakenTasks(@PathVariable long idProject){
		return serviceTaches.getNonTakenTasks(idProject);
	}

}
