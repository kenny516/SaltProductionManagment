package com.analytique.gestion_analytique.database.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
  
  @ManyToOne
  @JoinColumn(name = "idRubrique")
  private Rubrique rubrique;

	@OneToMany
  @JoinTable(
    name = "AssoDepensesParts",
    joinColumns = @JoinColumn(name = "idDepense"),
    inverseJoinColumns = @JoinColumn(name = "idPart")
  )
  private Set<PartsParCentre> partsParCentre;
  
  // Getters
  public Long getId() {
    return id;
  }
  
  public Date getDateDepense() {
    return dateDepense;
  }
  
  public BigDecimal getPrixU() {
    return prixU;
  }
  
  public Double getQuantite() {
    return quantite;
  }
  
  public Rubrique getRubrique() {
    return rubrique;
  }
  
  // Setters
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setDateDepense(Date dateDepense) {
    this.dateDepense = dateDepense;
  }
  
  public void setPrixU(BigDecimal prixU) {
    this.prixU = prixU;
  }
  
  public void setQuantite(Double quantite) {
    this.quantite = quantite;
  }

	public void setRubrique(Rubrique rubrique) {
		this.rubrique = rubrique;
	}

	public Set<PartsParCentre> getPartsParCentre() {
		return partsParCentre;
	}

	public void setPartsParCentre(Set<PartsParCentre> partsParCentre) {
		this.partsParCentre = partsParCentre;
	}

	
}
