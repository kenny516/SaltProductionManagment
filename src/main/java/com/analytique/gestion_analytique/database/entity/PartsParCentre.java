package com.analytique.gestion_analytique.database.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// PartsParCentre.java
@Entity
@Table(name = "PartsParCentre")
public class PartsParCentre {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "idRubrique")
  private Rubrique rubrique;
  
  @ManyToOne
  @JoinColumn(name = "idCentre")
  private Centre centre;
  
  @Column(name = "valeur")
  private BigDecimal valeur;
  
  @Column(name = "dateInsertion")
  private Date dateInsertion;
  
  // Getters and Setters
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public Rubrique getRubrique() {
    return rubrique;
  }
  
  public void setRubrique(Rubrique rubrique) {
    this.rubrique = rubrique;
  }
  
  public Centre getCentre() {
    return centre;
  }
  
  public void setCentre(Centre centre) {
    this.centre = centre;
  }
  
  public BigDecimal getValeur() {
    return valeur;
  }
  
  public void setValeur(BigDecimal valeur) {
    this.valeur = valeur;
  }
  
  public Date getDateInsertion() {
    return dateInsertion;
  }
  
  public void setDateInsertion(Date dateInsertion) {
    this.dateInsertion = dateInsertion;
  }
}
