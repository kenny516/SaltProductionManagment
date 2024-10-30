package com.analytique.gestion_analytique.dto.fromDatabase;

import java.util.ArrayList;
import java.util.List;

import com.analytique.gestion_analytique.database.entity.achat.Depenses;
import com.analytique.gestion_analytique.database.repository.PartsParCentreRepo;

import jakarta.persistence.EntityManager;

public class DepensesDTO extends Depenses {
	RubriqueDTO rubrique;
	List<PPCentreDTO> partsParCentres;

	public DepensesDTO(Depenses depenses, EntityManager em, PartsParCentreRepo PPcentreRepo) {
		this.setId(depenses.getId());
		this.setDateDepense(depenses.getDateDepense());
		this.setPrixU(depenses.getPrixU());
		this.setQuantite(depenses.getQuantite());
		this.setIdRubrique(depenses.getIdRubrique());
			if (PPcentreRepo != null && em != null) {
				setRubrique(new RubriqueDTO(getId(), em, PPcentreRepo));
				setPartsParCentres(em, PPcentreRepo);
			}
	}

	public DepensesDTO() {
		super();
	}

	public DepensesDTO(Integer id, EntityManager em, PartsParCentreRepo PPcentreRepo) {
		Depenses depenses = em.find(Depenses.class, id);
		this.setId(depenses.getId());
		this.setDateDepense(depenses.getDateDepense());
		this.setPrixU(depenses.getPrixU());
		this.setQuantite(depenses.getQuantite());
		this.setIdRubrique(depenses.getIdRubrique());
		if (PPcentreRepo != null) {
			setRubrique(new RubriqueDTO(getId(), em, PPcentreRepo));
			setPartsParCentres(em, PPcentreRepo);
		}
	}

	public static List<DepensesDTO> map(List<Depenses> depensesList, EntityManager em, PartsParCentreRepo PPcentreRepo) {
		List<DepensesDTO> depensesDTOs = new ArrayList<DepensesDTO>();
		for (Depenses depenses : depensesList) {
			DepensesDTO depensesDTO = new DepensesDTO(depenses, em,PPcentreRepo);
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

	public void setPartsParCentres(EntityManager em, PartsParCentreRepo parCentreRepo) {
		setPartsParCentres(PPCentreDTO.map(parCentreRepo.findByIdDepense(getId()), em));
	}

	public RubriqueDTO getRubrique() {
		return rubrique;
	}

	public void setRubrique(RubriqueDTO rubrique) {
		this.rubrique = rubrique;
	}

}
