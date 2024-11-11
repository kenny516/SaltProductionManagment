package com.analytique.gestion_analytique.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;

import java.util.List;

@Service
public class CandidatService {

    private CandidatRepository candidatRepository;


    public List<Candidat> getCandidatsRetenus(Long posteId) {
        return candidatRepository.findByPosteIdAndStatus(posteId, "Retenu");
    }
    
    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

		public List<Candidat> findAll(){
			return candidatRepository.findAll();
		}
}

