package com.analytique.gestion_analytique.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Repository.CandidatRepository;

import java.util.List;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    public List<Candidat> getCandidatsRetenus(Long posteId) {
        return candidatRepository.findByPosteIdAndStatus(posteId, "Retenu");
    }
}

