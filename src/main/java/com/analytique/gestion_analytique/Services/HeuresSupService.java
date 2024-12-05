package com.analytique.gestion_analytique.Services;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Models.HeuresSup;
import com.analytique.gestion_analytique.Models.HeuresSupSemaine;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.Repositories.HeuresSupRepository;
import com.analytique.gestion_analytique.Repositories.HeuresSupSemaineRepository;

@Service
public class HeuresSupService {

    @Autowired
    private HeuresSupRepository heuresSupRepository;
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    HeuresSupSemaineRepository heuresSupSemaineRepository;


    public double determinerTauxHoraire(HeuresSup hs, double tauxHoraireBase) {
        int dayOfWeek = hs.getDateDebut().getDayOfWeek().getValue();
        double coefficient;

        double heures = Duration.between(hs.getDateDebut(), hs.getDateFin()).toHours();

        // Vérification des jours spéciaux
        if (dayOfWeek == 7 & heures <= 5) { // Dimanche
            coefficient = 1.4;
            hs.setMajoration(40);
        } else if (isJourFerie(hs.getDateDebut())& heures <= 5) { // Jours fériés
            hs.setMajoration(100);
            coefficient = 2.0;
        } else {
            if (heures <= 8) {
                coefficient = 1.3;
                hs.setMajoration(30);
            } else {
                coefficient = 1.5;
                hs.setMajoration(50);
            }
        }

        return tauxHoraireBase * coefficient;
    }
    
    private boolean isJourFerie(LocalDateTime date) {
        // Extraire la date sans l'heure
        LocalDate jour = date.toLocalDate();
    
        // Liste des jours fériés fixes en fonction de l'année
        List<LocalDate> joursFeriesFixes = Arrays.asList(
            LocalDate.of(jour.getYear(), 1, 1),    // Nouvel An
            LocalDate.of(jour.getYear(), 5, 1),    // Fête du Travail
            LocalDate.of(jour.getYear(), 6, 26),   // Indépendance
            LocalDate.of(jour.getYear(), 12, 25)   // Noël
        );
        // Vérifier si la date donnée est un jour férié
        return joursFeriesFixes.contains(jour);
    }

    public HeuresSup creerHeureSup(HeuresSup heureSup) {

        Integer a = heureSup.getEmploye().getId();

        Optional<Employe> emp = employeRepository.findById(a);

        heureSup.setEmploye(emp.get());

        // Obtenir le premier jour de la semaine à partir de la date de début
        LocalDate dateDebut = heureSup.getDateDebut().toLocalDate();
        LocalDate premierJourSemaine = dateDebut.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        HeuresSupSemaine v_hs = heuresSupSemaineRepository.findBySemaineAndIdEmploye( premierJourSemaine, heureSup.getEmploye().getId());

        double heures = Duration.between(heureSup.getDateDebut(), heureSup.getDateFin()).toHours();
        heureSup.setTotalHeuresSup(heures);

        Double tauxHoraire = determinerTauxHoraire(heureSup, emp.get().getContrat().getTauxHoraire().doubleValue());
        heureSup.setTauxHoraire(tauxHoraire);
        heureSup.setMontant(tauxHoraire * heureSup.getTotalHeuresSup());

        Double totalheuresup = heures;
        if (v_hs != null) {
            totalheuresup += v_hs.getTotalHeuresSup();
        }

        if (totalheuresup <= 20) {
            return heuresSupRepository.save(heureSup);
        }

        return null;

    }

    public List<HeuresSup> getHeuresSupAfterToday() {
        LocalDate today = LocalDate.now();
        return heuresSupRepository.findHeuresSupAfterToday(today.atStartOfDay());
    }

    public double TotalmontantEmpHeursupMajore (Integer idEmploye, Integer mois, Integer annee, Integer majoration)
    {
        return heuresSupRepository.findTotalMontantByEmployeAndDateAndMajoration(idEmploye,mois,annee,majoration);
    }

}

