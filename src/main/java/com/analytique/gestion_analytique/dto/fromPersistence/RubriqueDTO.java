package com.analytique.gestion_analytique.dto.fromPersistence;

import java.util.List;

import com.analytique.gestion_analytique.database.entity.Depenses;
import com.analytique.gestion_analytique.database.entity.PartsParCentre;
import com.analytique.gestion_analytique.database.entity.Rubrique;
import com.analytique.gestion_analytique.database.entity.RubriqueCateg;
import com.analytique.gestion_analytique.database.entity.UniteOeuvre;
import com.analytique.gestion_analytique.service.PartsParCentreService;

import jakarta.persistence.EntityManager;

public class RubriqueDTO extends Rubrique{
	UniteOeuvre uniteOeuvre;
	RubriqueCateg rubriqueCateg;
	
	List<PartsParCentre> actu;
	List<Depenses> deps;

	public UniteOeuvre getUniteOeuvre() {
		return uniteOeuvre;
	}
	public void setUniteOeuvre(UniteOeuvre uniteOeuvre) {
		this.uniteOeuvre = uniteOeuvre;
	}
	public RubriqueCateg getRubriqueCateg() {
		return rubriqueCateg;
	}
	public void setRubriqueCateg(RubriqueCateg rubriqueCateg) {
		this.rubriqueCateg = rubriqueCateg;
	}

	public void setActu(PartsParCentreService ppcService) {
		actu = ppcService.getRecentPPCentre(this);
	}

	public RubriqueDTO(){
		super();
	}

	public RubriqueDTO(Rubrique rubrique, EntityManager em, PartsParCentreService ppcService) {
		setId(rubrique.getId());
		setNom(rubrique.getNom());
		setIdUniteOeuvre(rubrique.getIdUniteOeuvre());
		setIdCateg(rubrique.getIdCateg());
		if (em != null) {
			setUniteOeuvre(em.find(UniteOeuvre.class, rubrique.getIdUniteOeuvre()));
			setRubriqueCateg(em.find(RubriqueCateg.class, rubrique.getIdCateg()));
		}
		setActu(ppcService);
	}

	public RubriqueDTO(Long id, EntityManager em, PartsParCentreService ppcService) {
		Rubrique rubrique = em.find(Rubrique.class, id);
		setId(rubrique.getId());
		setNom(rubrique.getNom());
		setIdUniteOeuvre(rubrique.getIdUniteOeuvre());
		setIdCateg(rubrique.getIdCateg());
		if (em != null) {
			setUniteOeuvre(em.find(UniteOeuvre.class, rubrique.getIdUniteOeuvre()));
			setRubriqueCateg(em.find(RubriqueCateg.class, rubrique.getIdCateg()));
		}
		setActu(ppcService);
	}

	public static List<RubriqueDTO> map(List<Rubrique> rubriques, EntityManager em, PartsParCentreService ppcService) {
		return rubriques.stream().map(r -> new RubriqueDTO(r, em, ppcService)).toList();
	}
}
