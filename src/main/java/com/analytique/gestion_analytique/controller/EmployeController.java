package com.analytique.gestion_analytique.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.ContratEmploye;
import com.analytique.gestion_analytique.Models.PayeDetails;
import com.analytique.gestion_analytique.Models.RuptureContrat;
import com.analytique.gestion_analytique.Repositories.CategoriePersonnelRepository;
import com.analytique.gestion_analytique.Services.EmployeService;
import com.analytique.gestion_analytique.dto.receive.RemboursementReste;
import com.analytique.gestion_analytique.dto.send.EmployeSend;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/employe")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeController {

	EmployeService employeService;
	CategoriePersonnelRepository categoriePersonnelRepository;

	public EmployeController(EmployeService employeService, CategoriePersonnelRepository categoriePersonnelRepository) {
		this.employeService = employeService;
		this.categoriePersonnelRepository = categoriePersonnelRepository;
	}

	@GetMapping("")
	public List<EmployeSend> getAll() {
		return employeService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getbyId(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(employeService.getOne(id));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@GetMapping("/categories-personnel")
	public ResponseEntity<?> getCategoriePersonnel() {
		return ResponseEntity.ok(categoriePersonnelRepository.findAll());
	}
	

	@GetMapping("/poste/{id}")
	public List<EmployeSend> getEmployeByPoste(@PathVariable Integer id) {
		return employeService.getQualifiedEmployeesForPost(id);
	}

	@PostMapping("/contrat")
	public ResponseEntity<?> modifierContrat(@RequestBody ContratEmploye contrat) {

		return ResponseEntity.ok(employeService.modifierContrat(contrat.getEmploye().getId(),contrat.getDateDebut(),contrat.getTypeContrat(),contrat.getPoste(),contrat.getSalaire()));
	}

	@PostMapping("/rupture")
	public ResponseEntity<?> terminerContrat(@RequestBody RuptureContrat c) {
		return ResponseEntity.ok(employeService.rompreContrat(c));
	}
	

	@GetMapping("/{id}/avances")
	public List<RemboursementReste> getAllAvances(@PathVariable Integer id,
			@RequestParam(required = false, name = "unpaid") Boolean unpaid) {
		return employeService.getAllAvances(id, unpaid);
	}

	@PostMapping("/payer")
	public ResponseEntity<PayeDetails> payer(
			@RequestParam(name = "idEmploye") Integer id,
			@RequestParam(name = "datePaiement") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePaiement,
			@RequestParam(required = false, name = "heureNormale", defaultValue = "160.0") Double heureNormale) {
		try {
			PayeDetails paye = employeService.validerPaiement(id, datePaiement, heureNormale);
			return ResponseEntity.ok(paye);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
