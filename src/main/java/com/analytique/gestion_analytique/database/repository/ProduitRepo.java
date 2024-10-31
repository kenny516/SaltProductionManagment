package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.production.Produit;

public interface ProduitRepo extends JpaRepository<Produit,Integer>{

}
