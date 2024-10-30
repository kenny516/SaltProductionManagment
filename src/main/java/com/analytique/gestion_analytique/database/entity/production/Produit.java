package com.analytique.gestion_analytique.database.entity.production;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Produit.java
@Entity
@Table(name = "produit")
public class Produit {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "nom")
  private String nom;
  
  @Column(name = "iduniteoeuvre")
  private Integer idUniteOeuvre;
  
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
  
  public Integer getIdUniteOeuvre() {
    return idUniteOeuvre;
  }
  
  public void setIdUniteOeuvre(Integer idUniteOeuvre) {
    this.idUniteOeuvre = idUniteOeuvre;
  }
}
