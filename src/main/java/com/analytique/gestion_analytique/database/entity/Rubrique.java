package com.analytique.gestion_analytique.database.entity;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
  
  @ManyToOne
  @JoinColumn(name = "idUniteOeuvre")
  private UniteOeuvre uniteOeuvre;
  
  @ManyToOne
  @JoinColumn(name = "idCateg")
  private RubriqueCateg rubriqueCateg;
  
  @Column(name = "dateInsertion")
  private Date dateInsertion;
  
  @Transient
  private Set<PartsParCentre> parts;
	@Transient
	private Set<Depenses> depenses;

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
  
  public boolean isEstVariable() {
    return estVariable;
  }
  
  public void setEstVariable(boolean estVariable) {
    this.estVariable = estVariable;
  }
  
  public UniteOeuvre getUniteOeuvre() {
    return uniteOeuvre;
  }
  
  public void setUniteOeuvre(UniteOeuvre uniteOeuvre) {
    this.uniteOeuvre = uniteOeuvre;
  }
  
  public RubriqueCateg getRubriqueCateg() {
    return rubriqueCateg;
  }
  
  public void setRubriqueCateg(RubriqueCateg rubriqueCateg) {
    this.rubriqueCateg = rubriqueCateg;
  }
  
  public Date getDateInsertion() {
    return dateInsertion;
  }
  
  public void setDateInsertion(Date dateInsertion) {
    this.dateInsertion = dateInsertion;
  }

	public Set<PartsParCentre> getParts() {
		return parts;
	}

	public void setParts(Set<PartsParCentre> parts) {
		this.parts = parts;
	}

	
  public Set<Depenses> getDepenses() {
		return depenses;
	}

	public void setDepenses(Set<Depenses> depenses) {
		this.depenses = depenses;
	}
}
