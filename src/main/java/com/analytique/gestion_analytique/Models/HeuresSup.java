package com.analytique.gestion_analytique.Models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Heuressup")
public class HeuresSup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idEmploye;

    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    private Double totalHeuresSup;

    private Double tauxHoraire;

    private Double montant;

    public HeuresSup(Integer idEmploye, LocalDateTime dateDebut, LocalDateTime dateFin, Double totalHeuresSup,
            Double tauxHoraire, Double montant) {
        this.idEmploye = idEmploye;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.totalHeuresSup = totalHeuresSup;
        this.tauxHoraire = tauxHoraire;
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

    public Integer getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
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

    public Double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(Double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

}