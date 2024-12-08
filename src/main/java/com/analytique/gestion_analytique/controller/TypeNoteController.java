package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.TypeNote;
import com.analytique.gestion_analytique.Repositories.TypeNoteRepository;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/type-note")
@CrossOrigin(origins = "http://localhost:3000")
public class TypeNoteController {
	private TypeNoteRepository typeNoteRepository;

	public TypeNoteController(TypeNoteRepository typeNoteRepository) {
		this.typeNoteRepository = typeNoteRepository;
	}

	@GetMapping()
	public List<TypeNote> getAll() {
		return typeNoteRepository.findAll();
	}
	
}
