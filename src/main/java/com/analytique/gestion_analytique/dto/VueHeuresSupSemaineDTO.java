package com.analytique.gestion_analytique.dto;

import java.time.LocalDate;

public class VueHeuresSupSemaineDTO {
    private Integer idEmploye;
    private LocalDate semaine;
    private Double totalHeuresSup;
    private Double totalMontant;

    public VueHeuresSupSemaineDTO(Integer idEmploye, LocalDate semaine, Double totalHeuresSup, Double totalMontant) {
        this.idEmploye = idEmploye;
        this.semaine = semaine;
        this.totalHeuresSup = totalHeuresSup;
        this.totalMontant = totalMontant;
    }

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

