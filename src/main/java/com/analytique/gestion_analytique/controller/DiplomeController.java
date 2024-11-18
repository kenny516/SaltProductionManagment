package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Diplome;
import com.analytique.gestion_analytique.Repositories.DiplomesRepo;

@RestController
@RequestMapping("/api/diplomes")
public class DiplomeController {

	DiplomesRepo diplomesRepo;

	public DiplomeController(DiplomesRepo diplomesRepo) {
		this.diplomesRepo = diplomesRepo;
	}

	@GetMapping("")
	public List<Diplome> getAll(){
		return diplomesRepo.findAll();
	}
}
