package com.analytique.gestion_analytique.database.entity;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Rubrique.java
@Entity
@Table(name = "Rubrique")
public class Rubrique {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "nom")
  private String nom;
  
  @Column(name = "estVariable")
  private boolean estVariable;
  
  @Column(name = "idUniteOeuvre")
  private Long idUniteOeuvre;
  
  @Column(name = "idCateg")
  private Long idCateg;
  
  @Column(name = "dateInsertion")
  private Date dateInsertion;

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

	public boolean isEstVariable() {
		return estVariable;
	}

	public void setEstVariable(boolean estVariable) {
		this.estVariable = estVariable;
	}

	public Long getIdUniteOeuvre() {
		return idUniteOeuvre;
	}

	public void setIdUniteOeuvre(Long idUniteOeuvre) {
		this.idUniteOeuvre = idUniteOeuvre;
	}

	public Long getIdCateg() {
		return idCateg;
	}

	public void setIdCateg(Long idCateg) {
		this.idCateg = idCateg;
	}

	public Date getDateInsertion() {
		return dateInsertion;
	}

	public void setDateInsertion(Date dateInsertion) {
		this.dateInsertion = dateInsertion;
	}

	

}
