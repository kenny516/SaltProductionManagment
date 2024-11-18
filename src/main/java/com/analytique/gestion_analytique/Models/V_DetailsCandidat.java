package com.analytique.gestion_analytique.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "V_detailscandidat") 
public class V_DetailsCandidat {

    @Id
    private Integer candidatId; 

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "duree_experience_mois", columnDefinition = "integer")
    private Integer dureeExperienceMois;

    @Column(name = "plus_haut_niveau_diplome", columnDefinition = "integer")
    private Integer plusHautNiveauDiplome;

    // Getters et Setters

    public Integer getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Integer candidatId) {
        this.candidatId = candidatId;
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

    public Integer getDureeExperienceMois() {
        return dureeExperienceMois;
    }

    public void setDureeExperienceMois(Integer dureeExperienceMois) {
        this.dureeExperienceMois = dureeExperienceMois;
    }

    public Integer getPlusHautNiveauDiplome() {
        return plusHautNiveauDiplome;
    }

    public void setPlusHautNiveauDiplome(Integer plusHautNiveauDiplome) {
        this.plusHautNiveauDiplome = plusHautNiveauDiplome;
    }

    @Override
    public String toString() {
        return "VDetailsCandidat{" +
                "candidatId=" + candidatId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dureeExperienceMois=" + dureeExperienceMois +
                ", plusHautNiveauDiplome=" + plusHautNiveauDiplome +
                '}';
    }
}
