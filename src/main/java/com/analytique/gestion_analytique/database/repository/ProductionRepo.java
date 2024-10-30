package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.Production;

public interface ProductionRepo extends JpaRepository<Production,Long>{

}
