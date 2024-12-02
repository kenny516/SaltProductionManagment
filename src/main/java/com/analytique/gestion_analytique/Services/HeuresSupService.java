package com.analytique.gestion_analytique.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // Calcul du montant
        heureSup.setMontant(heureSup.getTauxHoraire() * heureSup.getTotalHeuresSup());
        return heuresSupRepository.save(heureSup);
    }

    public List<HeuresSup> getByEmployeAndWeek(Long idEmploye, LocalDate semaine) {
        // Appel au repository
        return heuresSupRepository.findByEmployeAndWeek(idEmploye, semaine);
    }
}

