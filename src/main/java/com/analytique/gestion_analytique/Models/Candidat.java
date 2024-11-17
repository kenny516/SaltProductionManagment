package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Candidats")
// pour avoir la liste des candidats qui passent pour l'entretien
// Pour l'appeler List<Candidat> qualifiedCandidats =
// candidatRepository.candidatReussiTest("TEST");

@NamedQuery(name = "Candidat.candidatReussiTest", query = "SELECT c FROM Candidat c " +
        "JOIN c.notes nc " +
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
	private List<NoteCandidat> notes;

	@OneToMany(mappedBy = "candidat")
	private List<Postulation> postulations;

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
		diplomes.forEach(d -> d.setCandidats(null));
		this.diplomes = diplomes;
	}

	public List<NoteCandidat> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteCandidat> noteCandidat) {
		noteCandidat.forEach(n -> n.setCandidat(null));
		this.notes = noteCandidat;
	}

	public void nullCandidat() {
		formations.forEach(f -> f.setCandidat(null));
		experiences.forEach(e -> e.setCandidat(null));
		diplomes.forEach(d -> d.setCandidats(null));
		notes.forEach(n -> n.setCandidat(null));
		postulations.forEach(p -> p.setCandidat(null));
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
		c.setId(null);
		c.setNom(this.nom);
		c.setPrenom(this.prenom);
		c.setEmail(this.email);
		c.setTelephone(this.telephone);
		return c;
	}
}
