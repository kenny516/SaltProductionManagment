package com.analytique.gestion_analytique.Models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Rupturecontrat")
public class RuptureContrat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_type_rupture", nullable = false)
	private TypeRupture typeRupture;

	@ManyToOne
	@JoinColumn(name = "id_employe", nullable = false)
	private Employe employe;

	@Column(name = "date_notification", nullable = false)
	private LocalDate dateNotification;

	@Column(name = "date_fin_contrat", nullable = false)
	private LocalDate dateFinContrat;

	@Column(name = "preavis_employe", nullable = false)
	private Boolean preavisEmploye;

	@Column(name = "preavis_entreprise", nullable = false)
	private Boolean preavisEntreprise;

	@Column(name = "motif")
	private String motif;

	@Column(name = "indemnite_verse", nullable = false, precision = 15, scale = 2)
	private BigDecimal indemniteVerse;

	// Constructeurs
	public RuptureContrat() {
	}

	public RuptureContrat(TypeRupture typeRupture, Employe employe, LocalDate dateNotification, LocalDate dateFinContrat,
			Boolean preavisEmploye, Boolean preavisEntreprise, String motif, BigDecimal indemniteVerse) {
		this.typeRupture = typeRupture;
		this.employe = employe;
		this.dateNotification = dateNotification;
		this.dateFinContrat = dateFinContrat;
		this.preavisEmploye = preavisEmploye;
		this.preavisEntreprise = preavisEntreprise;
		this.motif = motif;
		this.indemniteVerse = indemniteVerse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TypeRupture getTypeRupture() {
		return typeRupture;
	}

	public void setTypeRupture(TypeRupture typeRupture) {
		this.typeRupture = typeRupture;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public LocalDate getDateNotification() {
		return dateNotification;
	}

	public void setDateNotification(LocalDate dateNotification) {
		this.dateNotification = dateNotification;
	}

	public LocalDate getDateFinContrat() {
		return dateFinContrat;
	}

	public void setDateFinContrat(LocalDate dateFinContrat) {
		this.dateFinContrat = dateFinContrat;
	}

	public Boolean getPreavisEmploye() {
		return preavisEmploye;
	}

	public void setPreavisEmploye(Boolean preavisEmploye) {
		this.preavisEmploye = preavisEmploye;
	}

	public Boolean getPreavisEntreprise() {
		return preavisEntreprise;
	}

	public void setPreavisEntreprise(Boolean preavisEntreprise) {
		this.preavisEntreprise = preavisEntreprise;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public BigDecimal getIndemniteVerse() {
		return indemniteVerse;
	}

	public void setIndemniteVerse(BigDecimal indemniteVerse) {
		this.indemniteVerse = indemniteVerse;
	}

}