package com.analytique.gestion_analytique.Services;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CandidatsDiplomes;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.Experience;
import com.analytique.gestion_analytique.Models.Formation;
import com.analytique.gestion_analytique.Models.NoteCandidat;
import com.analytique.gestion_analytique.Models.NoteCandidatId;
import com.analytique.gestion_analytique.Models.Poste;
import com.analytique.gestion_analytique.Models.Postulation;
import com.analytique.gestion_analytique.Models.TypeNote;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.CandidatsDiplomeRepo;
import com.analytique.gestion_analytique.Repositories.CompetencesCandidatsRepository;
import com.analytique.gestion_analytique.Repositories.ExperienceRepo;
import com.analytique.gestion_analytique.Repositories.FormationRepo;
import com.analytique.gestion_analytique.Repositories.NoteCandidatRepository;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.Repositories.PostulationRepository;
import com.analytique.gestion_analytique.dto.receive.CandidatRecieve;
import com.analytique.gestion_analytique.dto.send.CandidatSend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidatService {
	@PersistenceContext
	private EntityManager em;

	private CandidatRepository candidatRepository;
	private PostulationRepository postulationRepository;
	private PosteRepository posteRepository;
	private CompetencesCandidatsRepository cCandidatsRepository;
	private NoteCandidatRepository noteCandidatRepository;
	private FormationRepo formationRepo;
	private ExperienceRepo experienceRepo;
	private CandidatsDiplomeRepo candidatsDiplomeRepo;

	public CandidatService(CandidatRepository candidatRepository, PostulationRepository postulationRepository,
			CompetencesCandidatsRepository cCandidatsRepository, NoteCandidatRepository noteCandidatRepository,
			FormationRepo formationRepo, ExperienceRepo experienceRepo, CandidatsDiplomeRepo candidatsDiplomeRepo) {
		this.candidatRepository = candidatRepository;
		this.posteRepository = posteRepository;
		this.postulationRepository = postulationRepository;
		this.cCandidatsRepository = cCandidatsRepository;
		this.noteCandidatRepository = noteCandidatRepository;
		this.formationRepo = formationRepo;
		this.experienceRepo = experienceRepo;
		this.candidatsDiplomeRepo = candidatsDiplomeRepo;
	}

	public List<Candidat> findAll() {
		List<Candidat> candidats = candidatRepository.findAllPostule();
		candidats.forEach(c -> c.nullCandidat());
		return candidats;
	}

	public List<Candidat> getCandidatsRetenus(Integer posteId) {
		// Récupérer toutes les postulations retenues pour un poste donné
		List<Postulation> postulationsRetenues = postulationRepository.findByPosteIdAndStatus(posteId, "Retenu");

		// Créer une liste pour stocker les candidats
		List<Candidat> candidatsRetenus = new ArrayList<>();

		// Ajouter les candidats associés aux postulations retenues
		for (Postulation postulation : postulationsRetenues) {
			candidatsRetenus.add(postulation.getCandidat());
		}
		// Retourner la liste des candidats retenus
		return candidatsRetenus;
	}

	public CandidatSend getById(Integer id) {
		Candidat c = candidatRepository.findById(id).get();
		List<CompetencesCandidats> cc = cCandidatsRepository.findByCandidatId(id);
		cc.forEach(com -> com.setCandidat(null));

		return new CandidatSend(c, cc);
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

	public List<Candidat> getElligibles(Integer posteId) {

		List<Candidat> posts = posteId == null ? candidatRepository.findElligibles()
				: candidatRepository.findElligiblesByPoste(posteId);

		posts.forEach(p -> p.nullCandidat());

		return posts;
	}

	@Transactional
	public Candidat saveCandidat(CandidatRecieve cd) {
		Candidat newCandidat = candidatRepository.save(cd.extractCandidat());
		Candidat candidat = new Candidat();
		candidat.setId(newCandidat.getId());

		List<Formation> formations = cd.getFormations();
		List<Experience> experiences = cd.getExperiences();
		List<CandidatsDiplomes> diplomes = cd.extractDiplomes();

		formations.forEach(exp -> exp.setCandidat(candidat));
		experiences.forEach(formation -> formation.setCandidat(candidat));
		diplomes.forEach(formation -> formation.setCandidat(candidat));

		formationRepo.saveAll(formations);
		experienceRepo.saveAll(experiences);
		candidatsDiplomeRepo.saveAll(diplomes);

		return newCandidat;
	}
	
	public Postulation PostulerPosteCandidat(PosatulationRecieve cd) {
		Candidat candidat = cd.extractCandidat(candidatRepository);
		Poste poste = cd.extractPoste(posteRepository);
	
		// Sauvegarde des compétences du candidat
		for (CompetencesCandidats competences : cd.extractCCandidat(em)) {
			competences.setCandidat(candidat);
			cCandidatsRepository.save(competences);
		}
	
		// Création et sauvegarde de la postulation
		Postulation postulation = new Postulation(candidat, poste, cd.getCandidatureTime());
		return postulationRepository.save(postulation);
	}

	public int login(String email, String mdp) {
		return candidatRepository.candidatExists(email, mdp);
	}
}
