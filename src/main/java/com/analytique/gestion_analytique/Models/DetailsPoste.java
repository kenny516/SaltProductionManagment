package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "detailsposte")
@IdClass(DetailsPosteId.class)
public class DetailsPoste {
    @Id
    @ManyToOne
    @JoinColumn(name = "idposte")
    private Poste poste;

    @Id
    @ManyToOne
    @JoinColumn(name = "idcompetence")
    private Competence competence;

}

