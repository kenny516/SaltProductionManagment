package com.analytique.gestion_analytique.Models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Heuressup")
public class HeuresSup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_employe",referencedColumnName = "id", nullable = false)
    private Employe employe;

    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    private Double totalHeuresSup;

    private Double montant;

    public HeuresSup(Employe employe, LocalDateTime dateDebut, LocalDateTime dateFin, Double totalHeuresSup, Double montant) {
        this.employe = employe;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.totalHeuresSup = totalHeuresSup;
        this.montant = montant;
    }

    public HeuresSup() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Double getTotalHeuresSup() {
        return totalHeuresSup;
    }

    public void setTotalHeuresSup(Double totalHeuresSup) {
        this.totalHeuresSup = totalHeuresSup;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

}
