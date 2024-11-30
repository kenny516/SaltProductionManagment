package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Competencescandidats")
@IdClass(CompetencesCandidatsId.class)
public class CompetencesCandidats {
	@Id
	@ManyToOne
	@JoinColumn(name = "idpostulation")
	private Postulation postulation;

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

    public Postulation getPostulation() {
        return postulation;
    }

    public void setPostulation(Postulation postulation) {
        this.postulation = postulation;
    }

}
