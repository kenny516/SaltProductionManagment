package com.analytique.gestion_analytique.Models;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "candidat_id", nullable = false)
    private Candidat candidat;

    private String message;

    @Column(name="date_heure")
    private Timestamp dateHeure;

    @Column(name = "statut_notification")
    private String statut;

    public Notification(Candidat candidat, String message, Timestamp dateHeure, String statut) {
        this.candidat = candidat;
        this.message = message;
        this.dateHeure = dateHeure;
        this.statut = statut;
    }

    public Notification() {
    }

    public Notification(Integer id, Candidat candidat, String message, Timestamp dateHeure, String statut) {
        this.id = id;
        this.candidat = candidat;
        this.message = message;
        this.dateHeure = dateHeure;
        this.statut = statut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Timestamp dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
