package com.analytique.gestion_analytique.dto.fromDatabase;

import java.util.List;

import com.analytique.gestion_analytique.database.entity.production.Produit;
import com.analytique.gestion_analytique.database.entity.unite.UniteOeuvre;

import jakarta.persistence.EntityManager;

public class ProduitDTO extends Produit {
	UniteOeuvre uniteOeuvre;

	public UniteOeuvre getUniteOeuvre() {
		return uniteOeuvre;
	}

	public void setUniteOeuvre(UniteOeuvre uniteOeuvre) {
		this.uniteOeuvre = uniteOeuvre;
	}

	public ProduitDTO() {
		super();
	}

	public ProduitDTO(Produit produit, EntityManager em) {
		setId(produit.getId());
		setNom(produit.getNom());
		setIdUniteOeuvre(produit.getIdUniteOeuvre());
		if (em != null) {
			setUniteOeuvre(em.find(UniteOeuvre.class, produit.getIdUniteOeuvre()));
		}
	}

	public ProduitDTO(Integer idProduit, EntityManager em) {
		Produit produit = em.find(Produit.class, idProduit);
		setId(produit.getId());
		setNom(produit.getNom());
		setIdUniteOeuvre(produit.getIdUniteOeuvre());
		setUniteOeuvre(em.find(UniteOeuvre.class, produit.getIdUniteOeuvre()));
	}

	public static List<ProduitDTO> map(List<Produit> produits, EntityManager em){
		return produits.stream().map(r -> new ProduitDTO(r, em)).toList();
	}

}
