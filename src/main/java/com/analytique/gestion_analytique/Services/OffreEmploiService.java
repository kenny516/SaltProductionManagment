package com.analytique.gestion_analytique.Services;

import java.util.List;

import com.analytique.gestion_analytique.Models.OffreEmploi;
import com.analytique.gestion_analytique.Repositories.OffreEmploiRepository;
import com.analytique.gestion_analytique.dto.receive.OffreEmploiRecieve;

public class OffreEmploiService {

    OffreEmploiRepository OeRepository;

    public List<OffreEmploi> getAll() {
        return OeRepository.findAll();
    }

    public OffreEmploi saveOffreEmploi(OffreEmploiRecieve offreEmploi) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveOffreEmploi'");
    }
    
}
