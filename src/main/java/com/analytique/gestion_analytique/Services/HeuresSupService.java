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
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.Repositories.HeuresSupRepository;
import com.analytique.gestion_analytique.dto.VueHeuresSupSemaineDTO;

@Service
public class HeuresSupService {

    @Autowired
    private HeuresSupRepository heuresSupRepository;
    @Autowired
    private EmployeRepository employeRepository;


    public double determinerTauxHoraire(LocalDateTime dateDebut, LocalDateTime dateFin, double tauxHoraireBase) {
        int dayOfWeek = dateDebut.getDayOfWeek().getValue();
        double coefficient;

        // Vérification des jours spéciaux
        if (dayOfWeek == 7) { // Dimanche
            coefficient = 1.4;
        } else if (isJourFerie(dateDebut)) { // Jours fériés
            coefficient = 2.0;
        } else {
            long heures = Duration.between(dateDebut, dateFin).toHours();
            if (heures <= 8) {
                coefficient = 1.3;
            } else {
                coefficient = 1.5;
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
    
        // Ajouter les jours fériés variables
        joursFeriesFixes.add(getLundiDePâques(jour.getYear()));
        joursFeriesFixes.add(getAscension(jour.getYear()));
        joursFeriesFixes.add(getPentecôte(jour.getYear()));
    
        // Vérifier si la date donnée est un jour férié
        return joursFeriesFixes.contains(jour);
    }
    
    private LocalDate getLundiDePâques(int annee) {
        // Calcul du dimanche de Pâques selon l'algorithme de Gauss
        int a = annee % 19;
        int b = annee / 100;
        int c = annee % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int mois = (h + l - 7 * m + 114) / 31;
        int jour = ((h + l - 7 * m + 114) % 31) + 1;
    
        // Le lundi de Pâques est le lendemain du dimanche de Pâques
        return LocalDate.of(annee, mois, jour).plusDays(1);
    }
    
    private LocalDate getAscension(int annee) {
        // L'Ascension est 39 jours après le dimanche de Pâques
        return getLundiDePâques(annee).minusDays(1).plusDays(39);
    }
    
    private LocalDate getPentecôte(int annee) {
        // La Pentecôte est 50 jours après le dimanche de Pâques
        return getLundiDePâques(annee).minusDays(1).plusDays(50);
    }


    public HeuresSup creerHeureSup(HeuresSup heureSup) {

        Integer a = heureSup.getEmploye().getId();

        Optional<Employe> emp = employeRepository.findById(a);

        // Obtenir le premier jour de la semaine à partir de la date de début
        LocalDate dateDebut = heureSup.getDateDebut().toLocalDate();
        LocalDate premierJourSemaine = dateDebut.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        VueHeuresSupSemaineDTO v_hs = heuresSupRepository.findVueHeuresSupByEmployeAndSemaine(heureSup.getEmploye().getId(), premierJourSemaine);

        double heures = Duration.between(heureSup.getDateDebut(), heureSup.getDateFin()).toHours();
        heureSup.setTotalHeuresSup(heures);

        Double tauxHoraire = determinerTauxHoraire(heureSup.getDateDebut(), heureSup.getDateFin(), emp.get().getContrat().getTauxHoraire().doubleValue());
        heureSup.setTauxHoraire(tauxHoraire);
        heureSup.setMontant(tauxHoraire * heureSup.getTotalHeuresSup());

        if (v_hs.getTotalHeuresSup() + heures <= 20) {
            return heuresSupRepository.save(heureSup);
        }

        return null;

    }

    public List<HeuresSup> getHeuresSupAfterToday() {
        LocalDate today = LocalDate.now();
        return heuresSupRepository.findHeuresSupAfterToday(today.atStartOfDay());
    }

}

