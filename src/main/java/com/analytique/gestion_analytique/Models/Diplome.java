package com.analytique.gestion_analytique.Models;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Diplome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diplome")
    private Integer id;

    private String diplome;

    private Integer niveau;

    @ManyToMany(mappedBy = "diplomes")
    private List<Candidat> candidats = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public List<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(List<Candidat> candidats) {
        this.candidats = candidats;
    }

}

