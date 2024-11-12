package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Services.EmployeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/employe")
public class EmployeController {

	EmployeService employeService;

	public EmployeController(EmployeService employeService) {
		this.employeService = employeService;
	}

	@GetMapping("")

	
	public List<Employe> getAll() {
		return employeService.getAll();
	}
}
