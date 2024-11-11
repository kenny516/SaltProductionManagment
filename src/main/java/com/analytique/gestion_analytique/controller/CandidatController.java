package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.Services.CandidatService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/candidat")
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
