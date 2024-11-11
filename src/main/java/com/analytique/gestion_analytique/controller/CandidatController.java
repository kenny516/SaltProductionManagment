package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.Services.CandidatService;
import com.analytique.gestion_analytique.dto.send.CandidatureData;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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

	@PostMapping("")
	public ResponseEntity<String> saveCandidat(@RequestBody CandidatureData candidature) {
		try {
			
		} catch (Exception e) {
			
		}
		
	}
	
}
