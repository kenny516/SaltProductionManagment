package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "soldeconge")
public class SoldeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_employe", nullable = false)
    private Employe Employe;

    @ManyToOne
    @JoinColumn(name = "id_type_conge", nullable = false)
    private TypeConge idTypeConge;

    @Column(name = "mois", nullable = false)
    private Integer mois;

    @Column(name = "annee", nullable = false)
    private Integer annee;

    @Column(name = "annee_fin", nullable = false)
    private Integer anneeFin;

}