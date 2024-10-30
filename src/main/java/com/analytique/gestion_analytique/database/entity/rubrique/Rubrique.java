package com.analytique.gestion_analytique.database.entity.rubrique;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Rubrique.java
@Entity
@Table(name = "rubrique")
public class Rubrique {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "nom")
  private String nom;
  
  @Column(name = "estvariable")
  private boolean estVariable;
  
  @Column(name = "iduniteoeuvre")
  private Integer idUniteOeuvre;
  
  @Column(name = "idcateg")
  private Integer idCateg;
  
  @Column(name = "dateinsertion")
  private Date dateInsertion;

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

	public boolean isEstVariable() {
		return estVariable;
	}

	public void setEstVariable(boolean estVariable) {
		this.estVariable = estVariable;
	}

	public Integer getIdUniteOeuvre() {
		return idUniteOeuvre;
	}

	public void setIdUniteOeuvre(Integer idUniteOeuvre) {
		this.idUniteOeuvre = idUniteOeuvre;
	}

	public Integer getIdCateg() {
		return idCateg;
	}

	public void setIdCateg(Integer idCateg) {
		this.idCateg = idCateg;
	}

	public Date getDateInsertion() {
		return dateInsertion;
	}

	public void setDateInsertion(Date dateInsertion) {
		this.dateInsertion = dateInsertion;
	}

	

}
