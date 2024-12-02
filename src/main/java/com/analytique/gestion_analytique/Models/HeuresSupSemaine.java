package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vue_heures_sup_semaine")
public class HeuresSupSemaine {

    @Id
    private Integer idEmploye;
    private LocalDate semaine;
    private Double totalHeuresSup;
    private Double totalMontant;

    // Getters and setters

    public Integer getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }

    public LocalDate getSemaine() {
        return semaine;
    }

    public void setSemaine(LocalDate semaine) {
        this.semaine = semaine;
    }

    public Double getTotalHeuresSup() {
        return totalHeuresSup;
    }

    public void setTotalHeuresSup(Double totalHeuresSup) {
        this.totalHeuresSup = totalHeuresSup;
    }

    public Double getTotalMontant() {
        return totalMontant;
    }

    public void setTotalMontant(Double totalMontant) {
        this.totalMontant = totalMontant;
    }
}
