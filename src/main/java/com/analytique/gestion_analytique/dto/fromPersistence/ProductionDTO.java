package com.analytique.gestion_analytique.dto.fromPersistence;


import java.util.List;

import com.analytique.gestion_analytique.database.entity.Production;

import jakarta.persistence.EntityManager;

public class ProductionDTO extends Production {
	ProduitDTO produit;

	public ProduitDTO getProduit() {
		return produit;
	}

	public void setProduit(ProduitDTO produit) {
		this.produit = produit;
	}
	
	public ProductionDTO() {
		super();
	}

	public ProductionDTO(Production production, EntityManager em){
		setId(production.getId());
		setDate(production.getDate());
		setIdProduit(production.getIdProduit());
		setQuantite(production.getQuantite());
		if (em!=null) {
			setProduit(new ProduitDTO(getIdProduit(), em));
		}
	}

	public ProductionDTO(Long idProduction, EntityManager em){
		Production production = em.find(Production.class, idProduction);
		setId(production.getId());
		setDate(production.getDate());
		setIdProduit(production.getIdProduit());
		setQuantite(production.getQuantite());

		setProduit(new ProduitDTO(getIdProduit(), em));
	}

	public static List<ProductionDTO> map(List<Production> productions, EntityManager em){
		return productions.stream().map(r -> new ProductionDTO(r, em)).toList();
	}

}
