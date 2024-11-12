package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/competence")
@CrossOrigin(origins = "http://localhost:3000")
public class CompetenceContoller {
	CompetenceRepository cRepo;

	public CompetenceContoller(CompetenceRepository cRepo) {
		this.cRepo = cRepo;
	}

	@GetMapping("")
	public List<Competence> getAll() {
		return cRepo.findAll();
	}

	@GetMapping("/poste/{id}")
	public List<Competence> getCompetencesByPoste(@PathVariable Integer id) {
		return cRepo.findByPoste(id);
	}
	
	
}
