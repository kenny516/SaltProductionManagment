package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Candidats")

public class Candidat {
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

	@Column(name = "mot_de_passe")
	private String motDePasse;

	@OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Formation> formations = new ArrayList<>();

	@OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Experience> experiences = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "Candidatsdiplomes", joinColumns = @JoinColumn(name = "candidat_id"), inverseJoinColumns = @JoinColumn(name = "diplome_id"))
	private List<Diplome> diplomes = new ArrayList<>();

	@OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Postulation> postulations;

	@OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompetencesCandidats> competencesCandidats;

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

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		formations.forEach(f -> f.setCandidat(null));
		this.formations = formations;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		experiences.forEach(e -> e.setCandidat(null));
		this.experiences = experiences;

	}

	public List<Diplome> getDiplomes() {
		return diplomes;
	}

	public void setDiplomes(List<Diplome> diplomes) {
		this.diplomes = diplomes;
	}

	public void nullCandidat() {
		formations.forEach(f -> f.setCandidat(null));
		experiences.forEach(e -> e.setCandidat(null));
		postulations.forEach(p -> p.setCandidat(null));
		setMotDePasse(null);
	}

	public List<Postulation> getPostulations() {
		return postulations;
	}

	public void setPostulations(List<Postulation> postulations) {
		postulations.forEach(p -> p.setCandidat(null));
		this.postulations = postulations;
	}

	public Candidat duplicateSimple(){
		Candidat c = new Candidat();
		c.setId(getId());
		c.setNom(this.nom);
		c.setPrenom(this.prenom);
		c.setEmail(this.email);
		c.setTelephone(this.telephone);
		return c;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	
}
