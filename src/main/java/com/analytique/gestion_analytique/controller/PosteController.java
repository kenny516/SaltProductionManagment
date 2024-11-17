package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.Poste;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.Services.PosteService;
import com.analytique.gestion_analytique.dto.receive.PosteRecieve;

import java.util.List;

@RestController
@RequestMapping("api/poste")
@CrossOrigin(origins = "http://localhost:3000")
public class PosteController {

	PosteService pService;

	

	public PosteController(PosteService pService) {
		this.pService = pService;
	}

	@GetMapping("")
	public List<Poste> getAll() {
		return pService.getAll();
	}

	@PostMapping("")
	public Poste savePoste(@RequestBody PosteRecieve poste) {
		return pService.savePoste(poste);
	}
	

}
