package com.analytique.gestion_analytique.service;

import java.util.List;
import java.util.Set;

import javax.swing.text.html.parser.Entity;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.database.entity.PartsParCentre;
import com.analytique.gestion_analytique.database.entity.Rubrique;
import com.analytique.gestion_analytique.database.repository.RubriqueRepo;
import com.analytique.gestion_analytique.dto.fromPersistence.RubriqueDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RubriqueService {
	@PersistenceContext
	private EntityManager entityManager;
	private final RubriqueRepo rubriqueRepo;
	private final PartsParCentreService ppcService;
	
	public RubriqueService(RubriqueRepo rubriqueRepo, PartsParCentreService ppcService) {
		this.rubriqueRepo = rubriqueRepo;
		this.ppcService = ppcService;
	}

	public List<RubriqueDTO> getAllRubriques() {
		return RubriqueDTO.map(rubriqueRepo.findAll(), entityManager, ppcService);
	}

	public Rubrique getRubriqueById(Long id) {
		return rubriqueRepo.findById(id).get();
	}

	public Rubrique saveRubrique(Rubrique rubrique) {
		return rubriqueRepo.save(rubrique);
	}
}
