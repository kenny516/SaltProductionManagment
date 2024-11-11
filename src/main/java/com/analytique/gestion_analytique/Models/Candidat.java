package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "Candidats")
// pour avoir la liste des candidats qui passent pour l'entretien
// Pour l'appeler List<Candidat> qualifiedCandidats = candidatRepository.candidatReussiTest("TEST");

@NamedQuery(
    name = "Candidat.findQualifiedByTypeNote",
    query = "SELECT c FROM Candidat c " +
            "JOIN c.noteCandidats nc " +
            "JOIN nc.typeNote tn " +
            "WHERE nc.note >= 6 AND tn.nomType = :nomType"
)
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(length = 20)
    private String telephone;

    @Column(name = "date_candidature", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate dateCandidature = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "poste_id")
    private Poste poste;

    @JoinColumn(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public LocalDate getDateCandidature() {
        return dateCandidature;
    }

    public void setDateCandidature(LocalDate dateCandidature) {
        this.dateCandidature = dateCandidature;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }
    
    public void employer()throws Exception{
        
    }
    

}
