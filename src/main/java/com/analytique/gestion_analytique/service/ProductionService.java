package com.analytique.gestion_analytique.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.database.entity.production.Production;
import com.analytique.gestion_analytique.database.repository.ProductionRepo;
import com.analytique.gestion_analytique.dto.fromDatabase.ProductionDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductionService {
	@PersistenceContext
	private EntityManager entityManager;

	private final ProductionRepo productionRepo;

	public ProductionService(ProductionRepo productionRepo) {
		this.productionRepo = productionRepo;
	}

	
	public List<ProductionDTO> getAll(){
		return ProductionDTO.map(productionRepo.findAll(), entityManager);
	}
	
	public ProductionDTO getById(Integer id) {
		Production production = productionRepo.findById(id).orElse(null);
		return production==null?null:new ProductionDTO(production,entityManager);
	}
	
	public ProductionDTO save(Production production) {
		production = productionRepo.save(production);
		return new ProductionDTO(production,entityManager);
	}
	
}
