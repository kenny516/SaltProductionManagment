package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Services.CompetenceService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/competence")
@CrossOrigin(origins = "http://localhost:3000")
public class CompetenceContoller {
	CompetenceService service;

	

	public CompetenceContoller(CompetenceService service) {
		this.service = service;
	}

	@GetMapping("")
	public List<Competence> getAll() {
		return service.findAll();
	}

	@GetMapping("/poste/{id}")
	public List<Competence> getCompetencesByPoste(@PathVariable Integer id) {
		return service.findByPoste(id);
	}
	
	
}
