package com.analytique.gestion_analytique.dto.fromDatabase;

import java.util.List;

import com.analytique.gestion_analytique.database.entity.achat.Depenses;
import com.analytique.gestion_analytique.database.entity.rubrique.PartsParCentre;
import com.analytique.gestion_analytique.database.entity.rubrique.Rubrique;
import com.analytique.gestion_analytique.database.entity.rubrique.RubriqueCateg;
import com.analytique.gestion_analytique.database.entity.unite.UniteOeuvre;
import com.analytique.gestion_analytique.database.repository.PartsParCentreRepo;

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

	public void setActu(PartsParCentreRepo ppcRepo) {
		actu = ppcRepo.findMostRecentByRubrique(getId());
	}

	public RubriqueDTO(){
		super();
	}

	public RubriqueDTO(Rubrique rubrique, EntityManager em, PartsParCentreRepo ppcRepo) {
		setId(rubrique.getId());
		setNom(rubrique.getNom());
		setIdUniteOeuvre(rubrique.getIdUniteOeuvre());
		setIdCateg(rubrique.getIdCateg());
		if (em != null) {
			setUniteOeuvre(em.find(UniteOeuvre.class, rubrique.getIdUniteOeuvre()));
			setRubriqueCateg(em.find(RubriqueCateg.class, rubrique.getIdCateg()));
		}
		setActu(ppcRepo);
	}

	public RubriqueDTO(Integer id, EntityManager em, PartsParCentreRepo ppcRepo) {
		Rubrique rubrique = em.find(Rubrique.class, id);
		setId(rubrique.getId());
		setNom(rubrique.getNom());
		setIdUniteOeuvre(rubrique.getIdUniteOeuvre());
		setIdCateg(rubrique.getIdCateg());
		if (em != null) {
			setUniteOeuvre(em.find(UniteOeuvre.class, rubrique.getIdUniteOeuvre()));
			setRubriqueCateg(em.find(RubriqueCateg.class, rubrique.getIdCateg()));
		}
		setActu(ppcRepo);
	}

	public static List<RubriqueDTO> map(List<Rubrique> rubriques, EntityManager em, PartsParCentreRepo ppcRepo) {
		return rubriques.stream().map(r -> new RubriqueDTO(r, em, ppcRepo)).toList();
	}
	public List<PartsParCentre> getActu() {
		return actu;
	}
	public void setActu(List<PartsParCentre> actu) {
		this.actu = actu;
	}
	public List<Depenses> getDeps() {
		return deps;
	}
	public void setDeps(List<Depenses> deps) {
		this.deps = deps;
	}

	
}
