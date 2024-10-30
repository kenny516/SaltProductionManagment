package com.analytique.gestion_analytique.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.database.entity.centre.Centre;
import com.analytique.gestion_analytique.database.entity.rubrique.PartsParCentre;
import com.analytique.gestion_analytique.database.entity.rubrique.Rubrique;
import com.analytique.gestion_analytique.database.repository.PartsParCentreRepo;
import com.analytique.gestion_analytique.dto.fromDatabase.PPCentreDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class PartsParCentreService {

	@PersistenceContext
	private EntityManager entityManager;
	private final PartsParCentreRepo ppcRepo;

	public PartsParCentreService(PartsParCentreRepo ppcRepo) {
		this.ppcRepo = ppcRepo;

	}

	/**
	 * Saves a PartsParCentre record
	 * 
	 * @param ppc the record to save
	 * @return the saved record
	 */
	public PPCentreDTO savePPCentre(PartsParCentre ppc) {
		return new PPCentreDTO(ppcRepo.save(ppc), entityManager);
	}

	/**
	 * Retrieves the most recent PartsParCentre records based on the specified
	 * Centre and Rubrique.
	 * It returns the most recent records for the given
	 * Rubrique across all centres.
	 *
	 * @param centre   the Centre.id to filter by, or null to retrieve records
	 *                 across all centres
	 * @param rubrique the Rubrique.id to filter by
	 * @return a set of the most recent PartsParCentre records
	 */
	public List<PartsParCentre> getRecentPPCentre( Rubrique rubrique) {
		return ppcRepo.findMostRecentByRubrique(rubrique.getId());
	}

/**
	 * Retrieves the most recent PartsParCentre records based on the specified
	 * Centre and Rubrique.
	 * It returns the most recent record for the specified Centre and
	 * Rubrique.
	 *
	 * @param centre   the Centre.id to filter by, or null to retrieve records
	 *                 across all centres
	 * @param rubrique the Rubrique.id to filter by
	 * @return a set of the most recent PartsParCentre records
	 */
	public PartsParCentre getRecentPPCentre(Centre centre, Rubrique rubrique) {
		return ppcRepo.findMostRecentByCentreAndRubrique(centre.getId(), rubrique.getId());
	}

}
