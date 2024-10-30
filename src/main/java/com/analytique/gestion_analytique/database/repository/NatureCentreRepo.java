package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.centre.NatureCentre;

public interface NatureCentreRepo extends JpaRepository<NatureCentre,Integer>{
}
