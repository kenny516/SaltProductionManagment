package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.Models.DetailsPoste;
import com.analytique.gestion_analytique.Models.DetailsPosteId;

public interface DetailsPosteRepository extends JpaRepository<DetailsPoste, DetailsPosteId>{
}
