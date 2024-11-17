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

	@Column(name = "status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@OneToMany(mappedBy = "candidat")
	private List<NoteCandidat> noteCandidat;

}
