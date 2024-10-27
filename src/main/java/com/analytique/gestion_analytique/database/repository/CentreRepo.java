package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.Centre;

/**
 * CentreRepo
 */
public interface CentreRepo extends JpaRepository<Centre,Long>{
	
}