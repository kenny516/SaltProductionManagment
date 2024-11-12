package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Services.EmployeService;

@RestController
@RequestMapping("/api/employe")
public class EmployeController {

	EmployeService employeService;

	public EmployeController(EmployeService employeService) {
		this.employeService = employeService;
	}

	public List<Employe> getAll() {
		return employeService.getAll();
	}
}
