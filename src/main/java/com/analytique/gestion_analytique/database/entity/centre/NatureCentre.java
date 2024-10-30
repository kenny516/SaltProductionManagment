package com.analytique.gestion_analytique.database.entity.centre;


import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// NatureCentre.java
@Entity
@Table(name = "naturecentre")
public class NatureCentre {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "nom")
  private String nom;
  
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
}
