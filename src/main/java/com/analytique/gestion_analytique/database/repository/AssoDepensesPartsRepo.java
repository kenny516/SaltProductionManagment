package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.association.AssoDepensePartsId;
import com.analytique.gestion_analytique.database.entity.association.AssoDepensesParts;

public interface AssoDepensesPartsRepo extends JpaRepository<AssoDepensesParts, AssoDepensePartsId> {

}
