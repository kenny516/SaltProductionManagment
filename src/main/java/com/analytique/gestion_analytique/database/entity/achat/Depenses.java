package com.analytique.gestion_analytique.database.entity.achat;

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
  private Integer id;
  
  @Column(name = "datedepense")
  private Date dateDepense;
  
  @Column(name = "prixu")
  private BigDecimal prixU;
  
  @Column(name = "quantite")
  private Double quantite;
  
  @Column(name = "idrubrique")
  private Integer idRubrique;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getIdRubrique() {
		return idRubrique;
	}

	public void setIdRubrique(Integer idRubrique) {
		this.idRubrique = idRubrique;
	}
}
