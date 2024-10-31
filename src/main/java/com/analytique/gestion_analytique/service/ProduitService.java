package com.analytique.gestion_analytique.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.database.entity.production.Produit;
import com.analytique.gestion_analytique.database.repository.ProduitRepo;
import com.analytique.gestion_analytique.dto.fromDatabase.ProduitDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProduitService {
	@PersistenceContext
	private EntityManager entityManager;

	private final ProduitRepo produitRepo;

	public ProduitService(ProduitRepo produitRepo) {
		this.produitRepo = produitRepo;
	}

	public List<ProduitDTO> getAll() {
		return ProduitDTO.map(produitRepo.findAll(), entityManager);
	}

	public ProduitDTO getById(Integer id) {
		return new ProduitDTO(produitRepo.findById(id).get(), entityManager);
	}

	public ProduitDTO save(Produit produit) {
		return new ProduitDTO(produitRepo.save(produit), entityManager);
	}
}
