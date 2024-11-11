package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Typecontrat")
public class TypeContrat {
    @Id
    private Integer id;

    @Column(name = "nomType", nullable = false, unique = true, length = 50)
    private String nomType;

    @Column(name = "dureeMois")
    private Integer dureeMois;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public Integer getDureeMois() {
        return dureeMois;
    }

    public void setDureeMois(Integer dureeMois) {
        this.dureeMois = dureeMois;
    }

}

