package com.analytique.gestion_analytique.dto.fromPersistence;

import java.util.ArrayList;
import java.util.List;


import com.analytique.gestion_analytique.database.entity.Depenses;
import com.analytique.gestion_analytique.database.entity.Rubrique;

import jakarta.persistence.EntityManager;

public class DepensesDTO extends Depenses {
	Rubrique rubrique;
	List<PPCentreDTO> partsParCentres;

	public Rubrique getRubrique() {
		return rubrique;
	}

	public void setRubrique(Rubrique rubrique) {
		this.rubrique = rubrique;
	}

	

	public DepensesDTO(Depenses depenses, EntityManager em) {
		this.setId(depenses.getId());
		this.setDateDepense(depenses.getDateDepense());
		this.setPrixU(depenses.getPrixU());
		this.setQuantite(depenses.getQuantite());
		this.setIdRubrique(depenses.getIdRubrique());
		if (em != null) {
			this.setRubrique(em.find(Rubrique.class, depenses.getIdRubrique()));
		}
	}

	public DepensesDTO() {
		super();
	}

	public DepensesDTO(Long id,EntityManager em) {
		Depenses depenses = em.find(Depenses.class, id);
		this.setId(depenses.getId());
		this.setDateDepense(depenses.getDateDepense());
		this.setPrixU(depenses.getPrixU());
		this.setQuantite(depenses.getQuantite());
		this.setIdRubrique(depenses.getIdRubrique());
		if (em != null) {
			this.setRubrique(em.find(Rubrique.class, depenses.getIdRubrique()));
		}
	}

	public static List<DepensesDTO> map(List<Depenses> depensesList, EntityManager em) {
		List<DepensesDTO> depensesDTOs = new ArrayList<DepensesDTO>();
		for (Depenses depenses : depensesList) {
			DepensesDTO depensesDTO = new DepensesDTO(depenses, em);
			depensesDTOs.add(depensesDTO);
		}
		return depensesDTOs;
	}

	public List<PPCentreDTO> getPartsParCentres() {
		return partsParCentres;
	}

	public void setPartsParCentres(List<PPCentreDTO> partsParCentres) {
		this.partsParCentres = partsParCentres;
	}

	// TODO : set the list of PPcentre via something else
}
