package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.Services.CandidatService;


@RestController
@RequestMapping("/api/candidat")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidatController {

	CandidatService candidatService;
	PosteRepository pRepo;
	CompetenceRepository cRepo;

	

	public CandidatController(CandidatService candidatService, PosteRepository pRepo, CompetenceRepository cRepo) {
		this.candidatService = candidatService;
		this.pRepo = pRepo;
		this.cRepo = cRepo;
	}

	@GetMapping("")
	public List<Candidat> getAll() {
		return candidatService.findAll();
	}
}
