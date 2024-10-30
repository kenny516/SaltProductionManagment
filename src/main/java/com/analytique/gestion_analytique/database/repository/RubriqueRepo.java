package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.rubrique.Rubrique;

import java.util.List;


public interface RubriqueRepo extends JpaRepository<Rubrique,Integer>{
	public List<Rubrique> findByIdCateg(Integer idCateg);
	
}
