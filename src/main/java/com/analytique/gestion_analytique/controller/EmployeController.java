package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Services.EmployeService;
import com.analytique.gestion_analytique.dto.send.EmployeData;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/employe")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeController {

	EmployeService employeService;

	public EmployeController(EmployeService employeService) {
		this.employeService = employeService;
	}

	@GetMapping("")
	public List<EmployeData> getAll() {
		return employeService.getAll();
	}
}
