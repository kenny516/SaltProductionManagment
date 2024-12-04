package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "Employes")
public class Employe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 100)
	private String nom;

	@Column(nullable = false, length = 100)
	private String prenom;

	@Column(nullable = false, length = 150, unique = true)
	private String email;

	@Column(length = 20)
	private String telephone;

	@Column(name = "date_embauche", columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private LocalDate dateEmbauche = LocalDate.now();

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "id_contrat_actuel")
	private ContratEmploye contrat;

	@OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
	// @JsonBackReference
	@JsonIgnore
	private List<Avance> Avances;

	@OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<BonusSalaire> bonusSalaires;

	public Employe(String nom, String prenom, String email, String telephone, LocalDate dateEmbauche,
			ContratEmploye contrat) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.dateEmbauche = dateEmbauche;
		this.contrat = contrat;
	}

	public Employe() {
	}

	public List<Avance> getAvances() {
		return Avances;
	}

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public ContratEmploye getContrat() {
		return contrat;
	}

	public void setContrat(ContratEmploye contrat) {
		this.contrat = contrat;
	}

	
}
