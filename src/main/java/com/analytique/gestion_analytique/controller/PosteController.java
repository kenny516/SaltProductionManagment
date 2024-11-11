package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Poste;
import com.analytique.gestion_analytique.Repositories.PosteRepository;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/poste")
public class PosteController {

	PosteRepository pRepo;

	public PosteController(PosteRepository pRepo) {
		this.pRepo = pRepo;
	}

	@GetMapping("")
	public List<Poste> getAll() {
		return pRepo.findAll();
	}

}
