package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import java.math.Double;

@Entity
@Table(name = "Paye")
public class Paye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employe", nullable = false)
    private Employe employe;

    @Column(nullable = false) 
    private int mois;

    @Column(nullable = false)
    private int annee;

    @Column(name = "heure_normale", nullable = false, precision = 5, scale = 2)
    private Double heureNormale;

    @Column(name = "heure_sup", precision = 20, scale = 2)
    private Double heureSup;

    @Column(precision = 20, scale = 2)
    private Double avance;

    @Column(name = "salaire_base", nullable = false, precision = 20, scale = 2)
    private Double salaireBase;

    @Column(nullable = false, precision = 20, scale = 2)
    private Double total;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Double getHeureNormale() {
        return heureNormale;
    }

    public void setHeureNormale(Double heureNormale) {
        this.heureNormale = heureNormale;
    }

    public Double getHeureSup() {
        return heureSup;
    }

    public void setHeureSup(Double heureSup) {
        this.heureSup = heureSup;
    }

    public Double getAvance() {
        return avance;
    }

    public void setAvance(Double avance) {
        this.avance = avance;
    }

    public Double getSalaireBase() {
        return salaireBase;
    }

    public void setSalaireBase(Double salaireBase) {
        this.salaireBase = salaireBase;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Paye(Long id, Employe employe, int mois, int annee, java.lang.Double heureNormale, java.lang.Double heureSup,
            java.lang.Double avance, java.lang.Double salaireBase, java.lang.Double total) {
        this.id = id;
        this.employe = employe;
        this.mois = mois;
        this.annee = annee;
        this.heureNormale = heureNormale;
        this.heureSup = heureSup;
        this.avance = avance;
        this.salaireBase = salaireBase;
        this.total = total;
    }

    public Paye(){}
}
