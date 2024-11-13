package com.analytique.gestion_analytique.Services;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.CompetencesCandidatsRepository;
import com.analytique.gestion_analytique.Repositories.NoteCandidatRepository;
import com.analytique.gestion_analytique.dto.receive.CandidatRecieve;
import com.analytique.gestion_analytique.dto.send.CandidatSend;

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
	private NoteCandidatRepository noteCandidatRepository;

	public CandidatService(EntityManager em, CandidatRepository candidatRepository,
			CompetencesCandidatsRepository cCandidatsRepository, NoteCandidatRepository noteCandidatRepository) {
		this.em = em;
		this.candidatRepository = candidatRepository;
		this.cCandidatsRepository = cCandidatsRepository;
		this.noteCandidatRepository = noteCandidatRepository;
	}

	public List<Candidat> findAll() {
		return candidatRepository.findAll();
	}

	public List<Candidat> getCandidatsRetenus(Integer posteId) {
		return candidatRepository.findByPosteIdAndStatus(posteId, "Retenu");
	}

	public Candidat saveCandidat(CandidatRecieve cd) {
		Candidat candidat = cd.extractCandidat(em);
		candidat = candidatRepository.save(candidat);

		for (CompetencesCandidats competences : cd.extractCCandidat(em)) {
			competences.setCandidat(candidat);
			cCandidatsRepository.save(competences);
		}

		return candidat;
	}

	public CandidatSend getById(Integer id) {
		Candidat c = candidatRepository.findById(id).get();
		List<CompetencesCandidats> cc = cCandidatsRepository.findByCandidatId(id);
		return new CandidatSend(c, cc.stream().map(CompetencesCandidats::getCompetence).collect(Collectors.toList()),noteCandidatRepository.findByCandidat(id));
	}

}
