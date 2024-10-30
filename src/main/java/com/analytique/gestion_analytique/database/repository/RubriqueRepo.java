package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.Rubrique;
import java.util.List;


public interface RubriqueRepo extends JpaRepository<Rubrique,Long>{
	public List<Rubrique> findByIdCateg(Long idCateg);
	
}
