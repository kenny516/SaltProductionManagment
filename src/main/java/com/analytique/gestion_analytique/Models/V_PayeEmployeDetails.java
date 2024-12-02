package com.analytique.gestion_analytique.Models;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "V_Paye_Employe_Details")
public class V_PayeEmployeDetails {

    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "email")
    private String email; 

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "date_embauche")
    private java.sql.Date dateEmbauche;

    @Column(name = "contrat_date_debut")
    private java.sql.Date contratDateDebut;

    @Column(name = "contrat_date_fin")
    private java.sql.Date contratDateFin;

    @Column(name = "contrat_salaire")
    private BigDecimal contratSalaire;

    @Column(name = "contrat_taux_horaire")
    private BigDecimal contratTauxHoraire;

    @Column(name = "poste_titre")
    private String posteTitre;

    @Column(name = "poste_description")
    private String posteDescription;

    @Column(name = "poste_departement")
    private String posteDepartement;

    @Column(name = "paye_mois")
    private Integer payeMois;

    @Column(name = "paye_annee")
    private Integer payeAnnee;

    @Column(name = "paye_heure_normale")
    private BigDecimal payeHeureNormale;

    @Column(name = "paye_heure_sup")
    private BigDecimal payeHeureSup;

    @Column(name = "paye_montant_heure_sup")
    private BigDecimal payeMontantHeureSup;

    @Column(name = "paye_salaire_base")
    private BigDecimal payeSalaireBase;

    @Column(name = "paye_avance")
    private BigDecimal payeAvance;

    @Column(name = "paye_nb_heure_abs")
    private BigDecimal payeNbHeureAbs;

    @Column(name = "paye_droit_conge")
    private BigDecimal payeDroitConge;

    @Column(name = "paye_droit_preavis")
    private BigDecimal payeDroitPreavis;

    @Column(name = "paye_indemnite")
    private BigDecimal payeIndemnite;

    @Column(name = "paye_prime_diverse")
    private BigDecimal payePrimeDiverse;

    @Column(name = "paye_irsa")
    private BigDecimal payeIrsa;

    @Column(name = "paye_cnaps")
    private BigDecimal payeCnaps;

    @Column(name = "paye_sanitaire")
    private BigDecimal payeSanitaire;

    @Column(name = "paye_total")
    private BigDecimal payeTotal;
}
