package com.analytique.gestion_analytique.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Models.HeuresSup;
import com.analytique.gestion_analytique.Repositories.HeuresSupRepository;

@Service
public class HeuresSupService {

    @Autowired
    private HeuresSupRepository heuresSupRepository;

    public Double determinerTaux(LocalDate semaine, Long idEmploye, Double heuresAjoutees) {
        List<HeuresSup> heuresSupSemaine = heuresSupRepository.findByEmployeAndWeek(idEmploye, semaine);

        double totalHeures = heuresSupSemaine.stream()
            .mapToDouble(HeuresSup::getTotalHeuresSup)
            .sum() + heuresAjoutees;

        if (totalHeures > 20) {
            throw new IllegalArgumentException("Les heures supplémentaires ne doivent pas dépasser 20 heures par semaine.");
        }

        if (heuresAjoutees <= 8) return 1.3;
        else if (heuresAjoutees <= 12) return 1.5;

        return 1.0;
    }

    public HeuresSup creerHeureSup(HeuresSup heureSup) {

        Integer a = heureSup.getIdEmploye().getId();

        Optional<Employe> emp = employeRepository.findById(a);

        // Obtenir le premier jour de la semaine à partir de la date de début
        LocalDate dateDebut = heureSup.getDateDebut().toLocalDate();
        LocalDate premierJourSemaine = dateDebut.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        HeuresSupSemaine v_hs = heuresSupSemaineRepository.findBySemaineAndIdEmploye( premierJourSemaine, heureSup.getIdEmploye().getId());

        double heures = Duration.between(heureSup.getDateDebut(), heureSup.getDateFin()).toHours();
        heureSup.setTotalHeuresSup(heures);

        Double tauxHoraire = determinerTauxHoraire(heureSup.getDateDebut(), heureSup.getDateFin(), emp.get().getContrat().getTauxHoraire().doubleValue());
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

    public List<HeuresSup> getByEmployeAndWeek(Long idEmploye, LocalDate semaine) {
        // Appel au repository
        return heuresSupRepository.findByEmployeAndWeek(idEmploye, semaine);
    }
}

