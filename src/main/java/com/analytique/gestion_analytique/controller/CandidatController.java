package com.analytique.gestion_analytique.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.Repositories.TypeNoteRepository;
import com.analytique.gestion_analytique.Services.CandidatService;
import com.analytique.gestion_analytique.Services.CandidatToEmpService;
import com.analytique.gestion_analytique.dto.receive.CandidatRecieve;

@RestController
@RequestMapping("/api/candidat")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidatController {

	CandidatService candidatService;
	CandidatToEmpService candidatToEmpService;
	PosteRepository pRepo;
	CompetenceRepository cRepo;
	TypeNoteRepository tnRepo;

	Map<String, Object> response = new HashMap<>();

	void clearResponse(){
		response.clear();
	}

	public CandidatController(CandidatService candidatService, CandidatToEmpService candidatToEmpService,
			PosteRepository pRepo, CompetenceRepository cRepo, TypeNoteRepository tnRepo) {
		this.candidatService = candidatService;
		this.candidatToEmpService = candidatToEmpService;
		this.pRepo = pRepo;
		this.cRepo = cRepo;
		this.tnRepo = tnRepo;
	}

	@GetMapping("")
	public List<Candidat> getAll() {
		return candidatService.findAll();
	}

	@PostMapping("")
	public ResponseEntity<?> saveCandidat(@RequestBody CandidatRecieve candidature) {
		clearResponse();
		try {
			response.put("id",candidatService.saveCandidat(candidature).getId());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("error", "");
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@PostMapping("/embaucher/{candidat}")
	public ResponseEntity<?> embaucher(@PathVariable Integer candidat) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(candidatToEmpService.embaucherCandidat(candidat));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Integer id) {
		clearResponse();
		try {
			response.put("value" , candidatService.getById(id));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@PostMapping("/note")
	public ResponseEntity<?> insertNote(@RequestBody Map<String,Integer> body) {
		int id = body.get("id");
		int typenote = body.get("typenote");
		int note = body.get("note");
		try {
			switch (candidatService.intsertNote(id, typenote, note)) {
				case 0:
					return ResponseEntity.status(HttpStatus.ACCEPTED).body("note updated");
				default:
					return ResponseEntity.status(HttpStatus.CREATED).body("note created");
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
