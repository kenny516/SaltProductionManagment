package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.Services.CandidatService;
import com.analytique.gestion_analytique.Services.CandidatToEmpService;
import com.analytique.gestion_analytique.dto.receive.CandidatureData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/candidat")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidatController {

	CandidatService candidatService;
	CandidatToEmpService candidatToEmpService;
	PosteRepository pRepo;
	CompetenceRepository cRepo;

	public CandidatController(CandidatService candidatService, PosteRepository pRepo, CompetenceRepository cRepo,
			CandidatToEmpService candidatToEmpService) {
		this.candidatService = candidatService;
		this.pRepo = pRepo;
		this.cRepo = cRepo;
		this.candidatToEmpService = candidatToEmpService;
	}

	@GetMapping("")
	public List<Candidat> getAll() {
		return candidatService.findAll();
	}

	@PostMapping("")
	public ResponseEntity<?> saveCandidat(@RequestBody CandidatureData candidature) {
		try {
			return ResponseEntity.ok(candidatService.saveCandidat(candidature));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/embaucher/{candidat}")
	public ResponseEntity<?> embaucher(@PathVariable Integer candidatId) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(candidatToEmpService.embaucherCandidat(candidatId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
