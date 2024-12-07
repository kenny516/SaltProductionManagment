package com.analytique.gestion_analytique.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Services.CandidatService;
import com.analytique.gestion_analytique.Models.Notification;
import com.analytique.gestion_analytique.Models.Postulation;
import com.analytique.gestion_analytique.Models.V_CandidatPostulation;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.Repositories.TypeNoteRepository;
import com.analytique.gestion_analytique.Services.CandidatToEmpService;
import com.analytique.gestion_analytique.Services.NotificationService;
import com.analytique.gestion_analytique.dto.receive.CandidatRecieve;
import com.analytique.gestion_analytique.dto.receive.PostulationRecieve;

@RestController
@RequestMapping("/api/candidat")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidatController {

	CandidatService candidatService;
	CandidatToEmpService candidatToEmpService;
	PosteRepository pRepo;
	CompetenceRepository cRepo;
	TypeNoteRepository tnRepo;
	NotificationService notificationService;

	Map<String, Object> response = new HashMap<>();

	void clearResponse() {
		response.clear();
	}

	public CandidatController(CandidatService candidatService, CandidatToEmpService candidatToEmpService, NotificationService notificationService,
			PosteRepository pRepo, CompetenceRepository cRepo, TypeNoteRepository tnRepo) {
		this.candidatService = candidatService;
		this.candidatToEmpService = candidatToEmpService;
		this.pRepo = pRepo;
		this.cRepo = cRepo;
		this.tnRepo = tnRepo;
		this.notificationService = notificationService;
	}

	@GetMapping("")
	public List<V_CandidatPostulation> getAll() {
		return candidatService.findAll();
	}

	@PostMapping("/postuler")
	public ResponseEntity<?> PostulerPosteCandidat(@RequestBody PostulationRecieve candidature) {
		clearResponse();
		try {
			Postulation postulation = candidatService.PostulerPosteCandidat(candidature);
			response.put("id", postulation.getId()); // Récupère l'ID de la postulation
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("error", "500");
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> postMethodName(@RequestBody HashMap<String, String> params) {
		clearResponse();
		try {
			response.put("id", candidatService.login(params.get("email"), params.get("password")));
			return ResponseEntity.ok(response);
		} catch (Exception e) {

			response.put("error", "500");
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
		try {
			return ResponseEntity.ok(candidatService.getById(id));
		} catch (Exception e) {

			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@PostMapping("/note")
	public ResponseEntity<?> insertNote(@RequestBody Map<String, Integer> body) {
		int id = body.get("idCandidat");
		int typenote = body.get("idTypeNote");
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

	@GetMapping("/elligibles/{id}")
	public List<V_CandidatPostulation> getMethodName(@PathVariable Integer id) {
		return candidatService.getElligibles(id);
	}

	@GetMapping("/elligibles")
	public List<V_CandidatPostulation> getMethodName() {
		return candidatService.getElligibles(null);
	}
	
	@GetMapping("/{id}/notification/read")
	public List<Notification> getRead(@PathVariable Integer id) {
		List<Notification> read = notificationService.getRead(id);
		read.forEach(nr -> nr.setCandidat(null));
		return read;
	}

	@GetMapping("/{id}/notification/non-read")
	public List<Notification> getNonRead(@PathVariable Integer id) {
		List<Notification> nonRead = notificationService.getNonRead(id);
		nonRead.forEach(nr -> notificationService.markAsRead(nr.getId()));
		nonRead.forEach(nr -> nr.setCandidat(null));
		return nonRead;
	}

	@PostMapping("")
	public ResponseEntity<?> register(@RequestBody CandidatRecieve c) {
		try {
			return ResponseEntity.ok(candidatService.saveCandidat(c));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/non-refus")
	public List<V_CandidatPostulation> getNonRefus(){
		return candidatService.findCandidatNonRefus();
	}

}
