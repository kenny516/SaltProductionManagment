package com.analytique.gestion_analytique.Services;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Models.Notification;
import com.analytique.gestion_analytique.Models.Postulation;
import com.analytique.gestion_analytique.Models.TypeContrat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.CompetencesEmployes;
import com.analytique.gestion_analytique.Models.ContratEmploye;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.Repositories.NotificationRepository;
import com.analytique.gestion_analytique.Repositories.PostulationRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.analytique.gestion_analytique.Repositories.CompetencesCandidatsRepository;
import com.analytique.gestion_analytique.Repositories.CompetencesEmployesRepository;
import com.analytique.gestion_analytique.Repositories.ContratEmployeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CandidatToEmpService {
	@PersistenceContext
	EntityManager entityManager;

	private PostulationRepository postulationRepository;
	private EmployeRepository employeRepository;
	private CompetencesCandidatsRepository competencesCandidatsRepository;
	private CompetencesEmployesRepository competencesEmployesRepository;
	private NotificationRepository notificationRepository;
	private ContratEmployeRepository contratEmployeRepository;


	public CandidatToEmpService(PostulationRepository postulationRepository, EmployeRepository employeRepository,
			CompetencesCandidatsRepository competencesCandidatsRepository,
			CompetencesEmployesRepository competencesEmployesRepository, NotificationRepository notificationRepository,
			ContratEmployeRepository contratEmployeRepository) {
		this.postulationRepository = postulationRepository;
		this.employeRepository = employeRepository;
		this.competencesCandidatsRepository = competencesCandidatsRepository;
		this.competencesEmployesRepository = competencesEmployesRepository;
		this.notificationRepository = notificationRepository;
		this.contratEmployeRepository = contratEmployeRepository;
	}



	@Transactional
	public Employe embaucherCandidat(Integer candidatId) {
		// Recherche de la postulation avec le statut "Retenu"
		Postulation postulation = postulationRepository.findByCandidatIdAndStatus(candidatId, "Retenu")
				.orElseThrow(() -> new IllegalArgumentException("Postulation non trouvée ou candidat non retenu"));

		// Création de l'employé basé sur la postulation
		Candidat candidat = postulation.getCandidat();
		Employe employe = new Employe(
				candidat.getNom(),
				candidat.getPrenom(),
				candidat.getEmail(),
				candidat.getTelephone(),
				LocalDate.now(),
				null);

		employe = employeRepository.save(employe);

		// Enregistrement des compétences liées à l'employé
		List<CompetencesCandidats> competencesCandidats = competencesCandidatsRepository.findByCandidatId(candidatId);
		for (CompetencesCandidats competenceCandidat : competencesCandidats) {
			CompetencesEmployes competenceEmploye = new CompetencesEmployes();
			competenceEmploye.setEmploye(employe);
			competenceEmploye.setCompetence(competenceCandidat.getCompetence());
			competenceEmploye.setNiveau(competenceCandidat.getNiveau());

			competencesEmployesRepository.save(competenceEmploye);
		}

		postulationRepository.updateStatus(postulation.getId(), "employe");

		String notifMessage = "Vous avez été retenu pour le poste de " + postulation.getOffreEmploi().getPoste().getTitre();
		Notification notification = new Notification(candidat, notifMessage, Timestamp.valueOf(LocalDateTime.now()),
				"non_lu");

		notificationRepository.save(notification);

		ContratEmploye contrat = new ContratEmploye();
		contrat.setEmploye(employe);
		contrat.setPoste(postulation.getOffreEmploi().getPoste());
		contrat.setSalaire(postulation.getOffreEmploi().getSalaire());
		contrat.setTypeContrat(entityManager.getReference(TypeContrat.class, 2));
		contrat.setDateDebut(employe.getDateEmbauche());

		contratEmployeRepository.save(contrat);

		

		return employe;
	}

}
