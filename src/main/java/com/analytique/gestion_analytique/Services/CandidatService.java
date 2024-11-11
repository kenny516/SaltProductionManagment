package com.analytique.gestion_analytique.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.CompetencesCandidatsRepository;
import com.analytique.gestion_analytique.dto.send.CandidatureData;

import java.util.List;

@Service
public class CandidatService {

	private CandidatRepository candidatRepository;
	private CompetencesCandidatsRepository cCandidatsRepository;

	public CandidatService(CandidatRepository candidatRepository,CompetencesCandidatsRepository c) {
		this.candidatRepository = candidatRepository;
		this.cCandidatsRepository = c;
	}

	public List<Candidat> findAll() {
		return candidatRepository.findAll();
	}

	public List<Candidat> getCandidatsRetenus(Integer posteId) {
		return candidatRepository.findByPosteIdAndStatus(posteId, "Retenu");
	}

	public Candidat saveCandidat(CandidatureData cd){
		if (cd.getCandidat().getPoste() == null) {
			throw new IllegalArgumentException("poste null");
		}
		Candidat candidat = candidatRepository.save(cd.getCandidat());

		for (CompetencesCandidats competences : cd.getCompetences()) {
			competences.setCandidat(candidat);
			competences = cCandidatsRepository.save(competences);
		}

		return candidat;
	}

}
