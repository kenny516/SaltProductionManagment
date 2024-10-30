package com.analytique.gestion_analytique.database.entity.rubrique;

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
@Table(name = "partsparcentre")
public class PartsParCentre {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "idrubrique")
  private Integer idRubrique;
  
  @Column(name = "idcentre")
  private Integer idCentre;
  
  @Column(name = "valeur")
  private BigDecimal valeur;
  
  @Column(name = "dateinsertion")
  private Date dateInsertion;
  
  // Getters and Setters
  public Integer getIdRubrique() {
    return idRubrique;
  }
  
  public void setIdRubrique(Integer idRubrique) {
    this.idRubrique = idRubrique;
  }
  
  public Integer getIdCentre() {
    return idCentre;
  }
  
  public void setIdCentre(Integer idCentre) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
