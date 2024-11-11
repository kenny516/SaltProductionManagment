package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/competence")
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
	public List<Competence> getCompetencesByPoste(@PathVariable Long id) {
		return cRepo.findByPoste(id);
	}
	
	
}
