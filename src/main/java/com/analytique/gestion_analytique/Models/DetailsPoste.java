package com.analytique.gestion_analytique.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "detailsposte")
@IdClass(DetailsPosteId.class)
public class DetailsPoste {
    @Id
    @ManyToOne
    @JoinColumn(name = "idposte")
		@JsonBackReference
    private Poste poste;

    @Id
    @ManyToOne
    @JoinColumn(name = "idcompetence")
    private Competence competence;

		public Poste getPoste() {
			return poste;
		}

		public void setPoste(Poste poste) {
			this.poste = poste;
		}

		public Competence getCompetence() {
			return competence;
		}

		public void setCompetence(Competence competence) {
			this.competence = competence;
		}

		

}

