package com.analytique.gestion_analytique.dto.fromDatabase;

import java.util.List;

import com.analytique.gestion_analytique.database.entity.centre.Centre;
import com.analytique.gestion_analytique.database.entity.centre.NatureCentre;

import jakarta.persistence.EntityManager;

public class CentreDTO extends Centre {
	NatureCentre natureCentre;

	public NatureCentre getNatureCentre() {
		return natureCentre;
	}

	public void setNatureCentre(NatureCentre natureCentre) {
		this.natureCentre = natureCentre;
	}

	public CentreDTO() {
		super();
	}

	public CentreDTO(Centre centre, EntityManager em) {
		this.setId(centre.getId());
		this.setIdNature(centre.getIdNature());
		this.setNom(centre.getNom());
		if (em != null) {
			this.setNatureCentre(em.find(NatureCentre.class, centre.getIdNature()));
		}
	}

	public static List<CentreDTO> map(List<Centre> centres, EntityManager em) {
		return centres.stream().map(r -> new CentreDTO(r, em)).toList();
	}

	public CentreDTO(Integer idCentre, EntityManager em) {
		Centre centre = em.find(Centre.class, idCentre);
		this.setId(centre.getId());
		this.setIdNature(centre.getIdNature());
		this.setNom(centre.getNom());
		this.setNatureCentre(em.find(NatureCentre.class, centre.getIdNature()));
	}
}
