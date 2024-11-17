package com.analytique.gestion_analytique.Services;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.CompetencesEmployes;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.Repositories.CompetencesCandidatsRepository;
import com.analytique.gestion_analytique.Repositories.CompetencesEmployesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CandidatToEmpService {
    private CandidatRepository candidatRepository;
    private EmployeRepository employeRepository;
    private CompetencesCandidatsRepository competencesCandidatsRepository;
    private CompetencesEmployesRepository competencesEmployesRepository;

		

    public CandidatToEmpService(CandidatRepository candidatRepository, EmployeRepository employeRepository,
				CompetencesCandidatsRepository competencesCandidatsRepository,
				CompetencesEmployesRepository competencesEmployesRepository) {
			this.candidatRepository = candidatRepository;
			this.employeRepository = employeRepository;
			this.competencesCandidatsRepository = competencesCandidatsRepository;
			this.competencesEmployesRepository = competencesEmployesRepository;
		}



		@Transactional
    public Employe embaucherCandidat(Integer candidatId) {
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new IllegalArgumentException("Candidat non trouvé"));

        Employe employe = new Employe(
                candidat.getNom(),
                candidat.getPrenom(),
                candidat.getEmail(),
                candidat.getTelephone(),
                candidat.getDateCandidature(),
                candidat.getPoste()
        );

        employe = employeRepository.save(employe);

        List<CompetencesCandidats> competencesCandidats = competencesCandidatsRepository.findByCandidatId(candidatId);

        for (CompetencesCandidats competenceCandidat : competencesCandidats) {
            CompetencesEmployes competenceEmploye = new CompetencesEmployes();
            competenceEmploye.setEmploye(employe);
            competenceEmploye.setCompetence(competenceCandidat.getCompetence());
            competenceEmploye.setNiveau(competenceCandidat.getNiveau());

            competencesEmployesRepository.save(competenceEmploye);
        }
				candidat.setStatus("Embauché");
				candidatRepository.save(candidat);
        return employe;
    }
}
