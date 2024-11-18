package com.analytique.gestion_analytique.Services;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.V_DetailsCandidat;

@Service
public class V_detailsCandidatService {

    public static void trierParExperienceEtNiveau(List<V_DetailsCandidat> candidats) {
        if (candidats != null) {
            candidats.sort(Comparator
                .comparingInt(V_DetailsCandidat::getDureeExperienceMois).reversed() 
                .thenComparingInt(V_DetailsCandidat::getPlusHautNiveauDiplome).reversed() 
            );
        }
    }
    
    public static void trierParNiveauEtExperience(List<V_DetailsCandidat> candidats) {
        if (candidats != null) {
            candidats.sort(Comparator
                .comparingInt(V_DetailsCandidat::getPlusHautNiveauDiplome).reversed() 
                .thenComparingInt(V_DetailsCandidat::getDureeExperienceMois).reversed()
            );
        }
    }
}
