package com.analytique.gestion_analytique.Models;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "V_Paye_Employe_Details")
public class V_PayeEmployeDetails {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "date_embauche")
    private LocalDate dateEmbauche;

    @Column(name = "contrat_date_debut")
    private LocalDate contratDateDebut;

    @Column(name = "contrat_date_fin")
    private LocalDate contratDateFin;

    @Column(name = "salaire")
    private BigDecimal salaire;

    @Column(name = "taux_horaire")
    private BigDecimal tauxHoraire;

    @Column(name = "titre")
    private String titre;

    @Column(name = "poste_description")
    private String posteDescription;

    @Column(name = "poste_departement")
    private String posteDepartement;

    @Column(name = "mois")
    private Integer mois;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "heure_normale")
    private BigDecimal heureNormale;

    @Column(name = "heure_sup")
    private BigDecimal heureSup;

    @Column(name = "montant_heure_sup")
    private BigDecimal montantHeureSup;

    @Column(name = "salaire_base")
    private BigDecimal salaireBase;

    @Column(name = "avance")
    private BigDecimal avance;

    @Column(name = "nb_heure_abs")
    private BigDecimal nbHeureAbs;

    @Column(name = "droit_conge")
    private BigDecimal droitConge;

    @Column(name = "droit_preavis")
    private BigDecimal droitPreavis;

    @Column(name = "indemnite")
    private BigDecimal indemnite;

    @Column(name = "prime_diverse")
    private BigDecimal primeDiverse;

    @Column(name = "irsa")
    private BigDecimal irsa;

    @Column(name = "cnaps")
    private BigDecimal cnaps;

    @Column(name = "sanitaire")
    private BigDecimal sanitaire;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "irsa_5")
    private BigDecimal irsa5;

    @Column(name = "irsa_10")
    private BigDecimal irsa10;

    @Column(name = "irsa_15")
    private BigDecimal irsa15;

    @Column(name = "irsa_20")
    private BigDecimal irsa20;
}
