package com.analytique.gestion_analytique.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Centre.java
@Entity
@Table(name = "Centre")
public class Centre {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "nom")
  private String nom;
  
  @ManyToOne
  @JoinColumn(name = "idNature")
  private NatureCentre natureCentre;
  
  // Getters and Setters
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getNom() {
    return nom;
  }
  
  public void setNom(String nom) {
    this.nom = nom;
  }
  
  public NatureCentre getNatureCentre() {
    return natureCentre;
  }
  
  public void setNatureCentre(NatureCentre natureCentre) {
    this.natureCentre = natureCentre;
  }
}
