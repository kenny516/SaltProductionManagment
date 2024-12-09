package com.analytique.gestion_analytique.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class PayeDetailsDto {
private Integer id;
private Integer idEmploye; // Use the ID of the Employe
    private Integer mois;
    private Integer annee;
    private BigDecimal heureNormale;
    private BigDecimal heureSup;
    private BigDecimal salaireBase;
    private BigDecimal avance;
    private BigDecimal total;

    // Include fields from Employe if needed

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public BigDecimal getHeureNormale() {
        return heureNormale;
    }

    public void setHeureNormale(BigDecimal heureNormale) {
        this.heureNormale = heureNormale;
    }

    public BigDecimal getHeureSup() {
        return heureSup;
    }

    public void setHeureSup(BigDecimal heureSup) {
        this.heureSup = heureSup;
    }

    public BigDecimal getSalaireBase() {
        return salaireBase;
    }

    public void setSalaireBase(BigDecimal salaireBase) {
        this.salaireBase = salaireBase;
    }

    public BigDecimal getAvance() {
        return avance;
    }

    public void setAvance(BigDecimal avance) {
        this.avance = avance;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }


}
