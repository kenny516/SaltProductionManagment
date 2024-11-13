package com.analytique.gestion_analytique.Services;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.CompetencesCandidatsRepository;
import com.analytique.gestion_analytique.dto.receive.CandidatureData;
import com.analytique.gestion_analytique.dto.send.CandidatData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatService {
	@PersistenceContext
	private EntityManager em;

	private CandidatRepository candidatRepository;
	private CompetencesCandidatsRepository cCandidatsRepository;

	public CandidatService(CandidatRepository candidatRepository, CompetencesCandidatsRepository cCandidatsRepository) {
		this.candidatRepository = candidatRepository;
		this.cCandidatsRepository = cCandidatsRepository;
	}

	public List<Candidat> findAll() {
		return candidatRepository.findAll();
	}

	public List<Candidat> getCandidatsRetenus(Integer posteId) {
		return candidatRepository.findByPosteIdAndStatus(posteId, "Retenu");
	}

	public Candidat saveCandidat(CandidatureData cd) {
		Candidat candidat = cd.extractCandidat(em);
		candidat = candidatRepository.save(candidat);

		for (CompetencesCandidats competences : cd.extractCCandidat(em)) {
			competences.setCandidat(candidat);
			cCandidatsRepository.save(competences);
		}

		return candidat;
	}

	public CandidatData getById(Integer id) {
		Candidat c = candidatRepository.findById(id).get();
		List<CompetencesCandidats> cc = cCandidatsRepository.findByCandidatId(id);
		return new CandidatData(c, cc.stream().map(CompetencesCandidats::getCompetence).collect(Collectors.toList()));
	}

}
