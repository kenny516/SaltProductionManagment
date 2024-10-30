package com.analytique.gestion_analytique.database.entity.production;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Production.java
@Entity
@Table(name = "production")
public class Production {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "idproduit")
  private Integer idProduit;
  
  @Column(name = "date")
  private Date date;
  
  @Column(name = "quantite")
  private BigDecimal quantite;
  
  // Getters and Setters
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }

	public Integer getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}
	
}
