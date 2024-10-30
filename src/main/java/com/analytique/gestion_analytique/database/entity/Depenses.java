package com.analytique.gestion_analytique.database.entity;

import java.math.BigDecimal;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Depenses.java
@Entity
@Table(name = "Depenses")
public class Depenses {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "dateDepense")
  private Date dateDepense;
  
  @Column(name = "prixU")
  private BigDecimal prixU;
  
  @Column(name = "quantite")
  private Double quantite;
  
  @Column(name = "idRubrique")
  private Long idRubrique;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDepense() {
		return dateDepense;
	}

	public void setDateDepense(Date dateDepense) {
		this.dateDepense = dateDepense;
	}

	public BigDecimal getPrixU() {
		return prixU;
	}

	public void setPrixU(BigDecimal prixU) {
		this.prixU = prixU;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Long getIdRubrique() {
		return idRubrique;
	}

	public void setIdRubrique(Long idRubrique) {
		this.idRubrique = idRubrique;
	}
}
