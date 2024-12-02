package com.analytique.gestion_analytique.Models;

import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.Double;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "Paye_Details")
@Data
// @AllArgsConstructor
@NoArgsConstructor
public class PayeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_employe")
    private Employe employe;

    @Column(nullable = false)
    private Integer mois;

    @Column(nullable = false)
    private Integer annee;

    @Column(name = "heure_normale", nullable = false, precision = 5, scale = 2)
    private BigDecimal heureNormale = BigDecimal.valueOf(160.0);

    @Column(name = "heure_sup", precision = 20, scale = 2)
    private BigDecimal heureSup;

    @Column(name = "montant_heure_sup", precision = 20, scale = 2)
    private BigDecimal MontantHeureSup;

    @Column(name = "salaire_base", nullable = false, precision = 20, scale = 2)
    private BigDecimal salaireBase;

    @Column(precision = 20, scale = 2)
    private BigDecimal avance;

    @Column(name = "nb_heure_abs", nullable = false, precision = 20, scale = 2)
    private BigDecimal nbHeureAbs = BigDecimal.ZERO;

    @Column(name = "droit_conge", nullable = false, precision = 20, scale = 2)
    private BigDecimal droitConge = BigDecimal.ZERO;

    @Column(name = "droit_preavis", nullable = false, precision = 20, scale = 2)
    private BigDecimal droitPreavis = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal indemnite = BigDecimal.ZERO;

    @Column(name = "prime_diverse", nullable = false, precision = 10, scale = 2)
    private BigDecimal primeDiverse = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal irsa = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cnaps;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sanitaire;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal total;

    public PayeDetails(Employe employe, Integer mois, Integer annee, 
                       BigDecimal heureNormale, BigDecimal heureSup, 
                       BigDecimal montantHeureSup, BigDecimal salaireBase, 
                       BigDecimal avance, BigDecimal nbHeureAbs, 
                       BigDecimal droitConge, BigDecimal droitPreavis, 
                       BigDecimal indemnite, BigDecimal primeDiverse, 
                       BigDecimal irsa, BigDecimal cnaps, 
                       BigDecimal sanitaire, BigDecimal total) {
        this.employe = employe;
        this.mois = mois;
        this.annee = annee;
        this.heureNormale = heureNormale != null ? heureNormale : BigDecimal.valueOf(160.0);
        this.heureSup = heureSup;
        this.MontantHeureSup = montantHeureSup;
        this.salaireBase = salaireBase;
        this.avance = avance;
        this.nbHeureAbs = nbHeureAbs != null ? nbHeureAbs : BigDecimal.ZERO;
        this.droitConge = droitConge != null ? droitConge : BigDecimal.ZERO;
        this.droitPreavis = droitPreavis != null ? droitPreavis : BigDecimal.ZERO;
        this.indemnite = indemnite != null ? indemnite : BigDecimal.ZERO;
        this.primeDiverse = primeDiverse != null ? primeDiverse : BigDecimal.ZERO;
        this.irsa = irsa != null ? irsa : BigDecimal.ZERO;
        this.cnaps = cnaps;
        this.sanitaire = sanitaire;
        this.total = total;
    }


    

}