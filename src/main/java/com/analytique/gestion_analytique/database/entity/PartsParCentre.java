package com.analytique.gestion_analytique.database.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// PartsParCentre.java
@Entity
@Table(name = "PartsParCentre")
public class PartsParCentre {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "idRubrique")
  private Long idRubrique;
  
  @Column(name = "idCentre")
  private Long idCentre;
  
  @Column(name = "valeur")
  private BigDecimal valeur;
  
  @Column(name = "dateInsertion")
  private Date dateInsertion;
  
  // Getters and Setters
  public Long getIdRubrique() {
    return idRubrique;
  }
  
  public void setIdRubrique(Long idRubrique) {
    this.idRubrique = idRubrique;
  }
  
  public Long getIdCentre() {
    return idCentre;
  }
  
  public void setIdCentre(Long idCentre) {
    this.idCentre = idCentre;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
