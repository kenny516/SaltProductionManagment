package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.TypeContrat;
import com.analytique.gestion_analytique.Repositories.TypeContratRepository;

@RestController
@RequestMapping("/api/type-contrat")
@CrossOrigin(origins = "http://localhost:3000")

public class TypeContratcontroller {
	
	private TypeContratRepository typeContratRepository;

	public TypeContratcontroller(TypeContratRepository typeContratRepository) {
		this.typeContratRepository = typeContratRepository;
	}

	@GetMapping()
	public List<TypeContrat> getAll(){
		return typeContratRepository.findAll();
	}

	
}
