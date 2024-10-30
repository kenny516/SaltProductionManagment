package com.analytique.gestion_analytique.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.database.entity.rubrique.Rubrique;
import com.analytique.gestion_analytique.database.repository.PartsParCentreRepo;
import com.analytique.gestion_analytique.database.repository.RubriqueRepo;
import com.analytique.gestion_analytique.dto.fromDatabase.RubriqueDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RubriqueService {
	@PersistenceContext
	private EntityManager entityManager;
	private final RubriqueRepo rubriqueRepo;
	private final PartsParCentreRepo parCentreRepo;
	
	public RubriqueService(RubriqueRepo rubriqueRepo, PartsParCentreRepo parCentreRepo) {
		this.rubriqueRepo = rubriqueRepo;
		this.parCentreRepo = parCentreRepo;
	}

	public List<RubriqueDTO> getAllRubriques() {
		return RubriqueDTO.map(rubriqueRepo.findAll(), entityManager, parCentreRepo);
	}

	public Rubrique getRubriqueById(Integer id) {
		return rubriqueRepo.findById(id).get();
	}

	public Rubrique saveRubrique(Rubrique rubrique) {
		return rubriqueRepo.save(rubrique);
	}
}
