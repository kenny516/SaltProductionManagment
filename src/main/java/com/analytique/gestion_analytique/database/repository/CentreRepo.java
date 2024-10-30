package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.centre.Centre;

/**
 * CentreRepo
 */
public interface CentreRepo extends JpaRepository<Centre,Integer>{
	
}