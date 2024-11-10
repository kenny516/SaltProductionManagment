package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "CompetencesEmployes")
@IdClass(CompetencesEmployesId.class)
public class CompetencesEmployes {
    @Id
    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    @Id
    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @Column(nullable = false)
    private Integer niveau;

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

}
