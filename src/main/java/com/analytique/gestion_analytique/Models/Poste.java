package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Postes")
public class Poste {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 100)
	private String titre;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(length = 100)
	private String departement;

	@ManyToOne
	@JoinColumn(name = "id_categorie_personnel")
	private CategoriePersonnel categoriePersonnel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public CategoriePersonnel getCategoriePersonnel() {
		return categoriePersonnel;
	}

	public void setCategoriePersonnel(CategoriePersonnel contrat) {
		this.categoriePersonnel = contrat;
	}

	

}
