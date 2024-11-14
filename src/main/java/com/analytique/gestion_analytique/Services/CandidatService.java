package com.analytique.gestion_analytique.Services;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.NoteCandidat;
import com.analytique.gestion_analytique.Models.NoteCandidatId;
import com.analytique.gestion_analytique.Models.TypeNote;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.CompetencesCandidatsRepository;
import com.analytique.gestion_analytique.Repositories.NoteCandidatRepository;
import com.analytique.gestion_analytique.Repositories.TypeNoteRepository;
import com.analytique.gestion_analytique.dto.CompetenceUser;
import com.analytique.gestion_analytique.dto.NoteUser;
import com.analytique.gestion_analytique.dto.receive.CandidatRecieve;
import com.analytique.gestion_analytique.dto.send.CandidatSend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatService {
	@PersistenceContext
	private EntityManager em;

	private CandidatRepository candidatRepository;
	private CompetencesCandidatsRepository cCandidatsRepository;
	private NoteCandidatRepository noteCandidatRepository;

	public CandidatService(CandidatRepository candidatRepository, CompetencesCandidatsRepository cCandidatsRepository,
			NoteCandidatRepository noteCandidatRepository) {
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
		Candidat candidat = cd.extractCandidat();
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
		List<CompetenceUser> comptences = cc.stream()
				.map(comp -> new CompetenceUser(comp.getCompetence(), comp.getCandidat().getId(), comp.getNiveau()))
				.collect(Collectors.toList());
		List<NoteUser> notes = noteCandidatRepository.findByCandidat(id).stream().map(nc -> new NoteUser(nc))
				.collect(Collectors.toList());
		return new CandidatSend(c, comptences, notes);
	}

	@Transactional
	public int intsertNote(int id, int type, int note) {
		NoteCandidat nc = new NoteCandidat();
		nc.setNote(note);
		nc.setTypeNote(em.getReference(TypeNote.class, type));
		nc.setCandidat(em.getReference(Candidat.class, id));

		int result = 1;
		if (noteCandidatRepository.existsById(new NoteCandidatId(id, type))) {
			noteCandidatRepository.deleteById(new NoteCandidatId(id, type));
			result = 0;
		}

		noteCandidatRepository.save(nc);
		return result;
	}

}
