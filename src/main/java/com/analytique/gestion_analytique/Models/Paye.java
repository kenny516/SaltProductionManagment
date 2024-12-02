package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.Double;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "Paye")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_employe", nullable = false)
    private Employe employe;

    @Column(nullable = false) 
    private int mois;

    @Column(nullable = false)
    private int annee;

    @Column(name = "heure_normale", nullable = false, precision = 5, scale = 2)
    private BigDecimal heureNormale;

    @Column(name = "heure_sup", precision = 20, scale = 2)
    private BigDecimal heureSup;

    @Column(precision = 20, scale = 2)
    private BigDecimal avance;

    @Column(name = "salaire_base", nullable = false, precision = 20, scale = 2)
    private BigDecimal salaireBase;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal total;

    // Getters and Setters


}
