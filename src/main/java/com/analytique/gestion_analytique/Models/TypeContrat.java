package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Typecontrat")
public class TypeContrat {
    @Id
    private Integer id;

    @Column(name = "nomtype", nullable = false, unique = true, length = 50)
    private String nom;

    @Column(name = "dureemois")
    private Integer dureeMois;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomType) {
        this.nom = nomType;
    }

    public Integer getDureeMois() {
        return dureeMois;
    }

    public void setDureeMois(Integer dureeMois) {
        this.dureeMois = dureeMois;
    }

}

