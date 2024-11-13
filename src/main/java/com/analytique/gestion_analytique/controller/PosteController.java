package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.Poste;
import com.analytique.gestion_analytique.Repositories.PosteRepository;

import java.util.List;

@RestController
@RequestMapping("api/poste")
@CrossOrigin(origins = "http://localhost:3000")
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
