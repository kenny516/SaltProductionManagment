package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_candidat_postulation")
public class V_CandidatPostulation {

    @Id
    private Integer id; // Primary key (Postulation ID)

    @Column(name = "candidat_id")
    private Integer candidatId;

    @Column(name = "offre_id")
    private Integer offreId;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @ManyToOne
    @JoinColumn(name = "poste_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Poste poste;

    @Column(name = "offre_status")
    private Boolean offreStatus;

    @Column(name = "date_postulation")
    private LocalDate datePostulation;

    private String status;


    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Integer candidatId) {
        this.candidatId = candidatId;
    }

    public Integer getOffreId() {
        return offreId;
    }

    public void setOffreId(Integer offreId) {
        this.offreId = offreId;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Boolean getOffreStatus() {
        return offreStatus;
    }

    public void setOffreStatus(Boolean offreStatus) {
        this.offreStatus = offreStatus;
    }

    public LocalDate getDatePostulation() {
        return datePostulation;
    }

    public void setDatePostulation(LocalDate datePostulation) {
        this.datePostulation = datePostulation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
