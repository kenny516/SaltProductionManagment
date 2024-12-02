package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Services.EmployeService;
import com.analytique.gestion_analytique.dto.send.EmployeSend;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
