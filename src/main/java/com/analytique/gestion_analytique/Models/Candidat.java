package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Candidats")
// pour avoir la liste des candidats qui passent pour l'entretien
// Pour l'appeler List<Candidat> qualifiedCandidats =
// candidatRepository.candidatReussiTest("TEST");

@NamedQuery(name = "Candidat.candidatReussiTest", query = "SELECT c FROM Candidat c " +
		"JOIN c.noteCandidat nc " +
		"JOIN nc.typeNote tn " +
		"WHERE nc.note >= 6 AND tn.nomType = :nomType")
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

	@OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Formation> formations = new ArrayList<>();

	@OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Experience> experiences = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "Candidatsdiplomes", joinColumns = @JoinColumn(name = "candidat_id"), inverseJoinColumns = @JoinColumn(name = "diplome_id"))
	private List<Diplome> diplomes = new ArrayList<>();

	@OneToMany(mappedBy = "candidat")
	private List<NoteCandidat> noteCandidat;

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
		this.formations = formations;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Diplome> getDiplomes() {
		return diplomes;
	}

	public void setDiplomes(List<Diplome> diplomes) {
		this.diplomes = diplomes;
	}

	public List<NoteCandidat> getNoteCandidat() {
		return noteCandidat;
	}

	public void setNoteCandidat(List<NoteCandidat> noteCandidat) {
		this.noteCandidat = noteCandidat;
	}

}
