package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Repositories.PosteRepository;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/poste")
public class PosteController {
	PosteRepository pRepo;
	

	public PosteController(PosteRepository pRepo) {
		this.pRepo = pRepo;
	}

	public savePoste(){

	}
}
