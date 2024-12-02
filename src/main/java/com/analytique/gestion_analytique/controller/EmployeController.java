package com.analytique.gestion_analytique.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.CategoriePersonnel;
import com.analytique.gestion_analytique.Repositories.CategoriePersonnelRepository;
import com.analytique.gestion_analytique.Services.EmployeService;
import com.analytique.gestion_analytique.dto.send.EmployeSend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/poste/{id}")
	public List<EmployeSend> getEmployeByPoste(@PathVariable Integer id) {
		return employeService.getQualifiedEmployeesForPost(id);
	}

	@GetMapping("/categories-personnel")
	public List<CategoriePersonnel> getCategoriesPersonnel() {
		List<CategoriePersonnel> fetched = categoriePersonnelRepository.findAll();

		List<CategoriePersonnel> categs = new ArrayList<>();
		
		CategoriePersonnel tous = new CategoriePersonnel();
		tous.setId(0);
		tous.setNom("Tous");
		tous.setDescription("Toutes les catégories de personnel");

		// categs.add(tous);

		categs.addAll(fetched);

		return categs;
	}
	
}
