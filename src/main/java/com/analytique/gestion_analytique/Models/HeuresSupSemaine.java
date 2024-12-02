package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class HeuresSupSemaine {

    @Id
    private Long idEmploye;
    private LocalDate semaine;
    private BigDecimal totalHeuresSup;
    private BigDecimal totalMontant;

    // Getters and setters

    public Long getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Long idEmploye) {
        this.idEmploye = idEmploye;
    }

    public LocalDate getSemaine() {
        return semaine;
    }

    public void setSemaine(LocalDate semaine) {
        this.semaine = semaine;
    }

    public BigDecimal getTotalHeuresSup() {
        return totalHeuresSup;
    }

    public void setTotalHeuresSup(BigDecimal totalHeuresSup) {
        this.totalHeuresSup = totalHeuresSup;
    }

    public BigDecimal getTotalMontant() {
        return totalMontant;
    }

    public void setTotalMontant(BigDecimal totalMontant) {
        this.totalMontant = totalMontant;
    }
}
