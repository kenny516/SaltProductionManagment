package com.analytique.gestion_analytique.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.database.entity.achat.Depenses;
import com.analytique.gestion_analytique.database.entity.association.AssoDepensesParts;
import com.analytique.gestion_analytique.database.entity.rubrique.PartsParCentre;
import com.analytique.gestion_analytique.database.repository.AssoDepensesPartsRepo;
import com.analytique.gestion_analytique.database.repository.DepensesRepo;
import com.analytique.gestion_analytique.database.repository.PartsParCentreRepo;
import com.analytique.gestion_analytique.dto.fromDatabase.DepensesDTO;
import com.analytique.gestion_analytique.dto.fromDatabase.RubriqueDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class DepensesService {
	@PersistenceContext
	private EntityManager entityManager;

	private final DepensesRepo depensesRepo;
	private final PartsParCentreRepo parCentreRepo;
	private final AssoDepensesPartsRepo aPartsRepo;

	public DepensesService(DepensesRepo depensesRepo, PartsParCentreRepo parCentreRepo,AssoDepensesPartsRepo aPartsRepo) {
		this.depensesRepo = depensesRepo;
		this.parCentreRepo = parCentreRepo;
		this.aPartsRepo = aPartsRepo;
	}

	public List<DepensesDTO> getAll(){
		return DepensesDTO.map(depensesRepo.findAll(), entityManager, parCentreRepo);
	}

	public DepensesDTO getById(Integer id){
		return new DepensesDTO(id, entityManager, parCentreRepo);
	}

	public DepensesDTO save(Depenses depenses){
		RubriqueDTO rubriqueDTO = new RubriqueDTO(depenses.getIdRubrique(), entityManager, parCentreRepo);

		depenses = depensesRepo.save(depenses);
		for (PartsParCentre actu : rubriqueDTO.getActu()) {
			AssoDepensesParts aParts = new AssoDepensesParts();
			aParts.setIdDepense(depenses.getId());
			aParts.setIdPart(actu.getId());

			aPartsRepo.save(aParts);
		}
		return new DepensesDTO(depenses, entityManager, parCentreRepo);
	}
}
