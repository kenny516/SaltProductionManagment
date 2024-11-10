package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "detailsPoste")
@IdClass(DetailsPosteId.class)
public class DetailsPoste {
    @Id
    @ManyToOne
    @JoinColumn(name = "idPoste")
    private Poste poste;

    @Id
    @ManyToOne
    @JoinColumn(name = "idCompetence")
    private Competence competence;

}

