package com.analytique.gestion_analytique.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.OffreEmploi;
import com.analytique.gestion_analytique.Repositories.OffreEmploiRepository;
import com.analytique.gestion_analytique.dto.receive.OffreEmploiRecieve;

@Service
public class OffreEmploiService {

    private OffreEmploiRepository oeRepository;

    public OffreEmploiService(OffreEmploiRepository oeRepository) {
        this.oeRepository = oeRepository;
    }

    public List<OffreEmploi> getAll() {
        return oeRepository.findAll();
    }

    public OffreEmploi saveOffreEmploi(OffreEmploiRecieve offreEmploi) {
        OffreEmploi oe = oeRepository.save(offreEmploi);
        return oe;
    }
    
}
