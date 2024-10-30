package com.analytique.gestion_analytique.database.entity.centre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Centre.java
@Entity
@Table(name = "Centre")
public class Centre {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "nom")
  private String nom;
  
  @Column(name = "idnature")
  private Integer idNature;
  
  // Getters and Setters
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getNom() {
    return nom;
  }
  
  public void setNom(String nom) {
    this.nom = nom;
  }
  
  public Integer getIdNature() {
    return idNature;
  }
  
  public void setIdNature(Integer idNature) {
    this.idNature = idNature;
  }
	
}
