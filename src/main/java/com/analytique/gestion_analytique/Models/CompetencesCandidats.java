package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Competencescandidats")
@IdClass(CompetencesCandidatsId.class)
public class CompetencesCandidats {
	@Id
	@ManyToOne
	@JoinColumn(name = "idCandidat")
	private Candidat Candidat;

    @Id
    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @Column(nullable = false)
    private Integer niveau;


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

    public Candidat getCandidat() {
        return Candidat;
    }

    public void setCandidat(Candidat Candidat) {
        this.Candidat = Candidat;
    }

}
