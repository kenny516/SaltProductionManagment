package com.analytique.gestion_analytique.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Paye;
import com.analytique.gestion_analytique.Services.EmployeService;
import com.analytique.gestion_analytique.dto.receive.RemboursementReste;
import com.analytique.gestion_analytique.dto.send.EmployeSend;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/employe")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeController {

	EmployeService employeService;

	public EmployeController(EmployeService employeService, JdbcTemplate jdbcTemplate) {
		this.employeService = employeService;
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

	@GetMapping("/poste/{id}")
	public List<EmployeSend> getEmployeByPoste(@PathVariable Integer id) {
		return employeService.getQualifiedEmployeesForPost(id);
	}

	@GetMapping("/{id}/avances")
	public List<RemboursementReste> getAllAvances(@PathVariable Integer id, @RequestParam(required = false, name = "unpaid") Boolean unpaid) {
		return employeService.getAllAvances(id, unpaid);
	}

	@PostMapping("/{id}/payer")
	public ResponseEntity<Paye> payer(
            @PathVariable Integer id,
            @RequestParam(name="datePaiement") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePaiement,
            @RequestParam(required = false, name="heureNormale", defaultValue = "160.0") Double heureNormale) {
        try {
            Paye paye = employeService.payer(id, datePaiement, heureNormale);
            return ResponseEntity.ok(paye);
        } catch (Exception e) {
			System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
}
