package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "typenote")
public class TypeNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomType", nullable = false, length = 50)
    private String nomType;

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

}

