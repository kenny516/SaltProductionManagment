package com.analytique.gestion_analytique.Services;

import java.util.List;

import com.analytique.gestion_analytique.Models.OffreEmploi;
import com.analytique.gestion_analytique.Repositories.OffreEmploiRepository;
import com.analytique.gestion_analytique.dto.receive.OffreEmploiRecieve;

public class OffreEmploiService {

    OffreEmploiRepository oeRepository;

    public List<OffreEmploi> getAll() {
        return oeRepository.findAll();
    }

    public OffreEmploi saveOffreEmploi(OffreEmploiRecieve offreEmploi) {
        OffreEmploi oe = oeRepository.save(offreEmploi);
        return oe;
    }
    
}
