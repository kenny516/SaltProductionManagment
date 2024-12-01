package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.OffreEmploi;
import com.analytique.gestion_analytique.Services.OffreEmploiService;
import com.analytique.gestion_analytique.dto.receive.OffreEmploiRecieve;

import java.util.List;

@RestController
@RequestMapping("api/OffreEmploi")
@CrossOrigin(origins = "http://localhost:3000")
public class OffreEmploiController {

	OffreEmploiService OeService;

	public OffreEmploiController(OffreEmploiService OeService) {
		this.OeService = OeService;
	}

	@GetMapping("")
	public List<OffreEmploi> getAll() {
		return OeService.getAll();
	}

	@PostMapping("")
	public OffreEmploi saveOffreEmploi(@RequestBody OffreEmploiRecieve OffreEmploi) {
		return OeService.saveOffreEmploi(OffreEmploi);
	}
	

}
