package com.analytique.gestion_analytique.dto.receive;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.Poste;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.dto.CompetenceUser;

import jakarta.persistence.EntityManager;

public class PostulationRecieve {
	int idCandidat,idPoste;	
	LocalDate candidatureTime;
	List<CompetenceUser> competences;

	public Candidat extractCandidat(CandidatRepository cR) {
		Candidat c = cR.findById(idCandidat).get();
		return c;
	}

	public Poste extractPoste(PosteRepository cP) {
		Poste p = cP.findById(idPoste).get();
		return p;
	}

	public List<CompetencesCandidats> extractCCandidat(EntityManager em) {
		List<CompetencesCandidats> csc = new ArrayList<CompetencesCandidats>();

		for (CompetenceUser competences : getCompetences()) {
			CompetencesCandidats cc = competences.extractCandidat(em);
			csc.add(cc);
		}

		return csc;
	}

	public List<CompetenceUser> getCompetences() {
		return competences;
	}

	public void setCompetences(List<CompetenceUser> competences) {
		this.competences = competences;
	}

	public int getIdCandidat() {
		return idCandidat;
	}

	public void setIdCandidat(int idCandidat) {
		this.idCandidat = idCandidat;
	}

	public int getIdPoste() {
		return idPoste;
	}

	public void setIdPoste(int idPoste) {
		this.idPoste = idPoste;
	}

	public LocalDate getCandidatureTime() {
		return candidatureTime;
	}

	public void setCandidatureTime(LocalDate candidatureTime) {
		this.candidatureTime = candidatureTime;
	}

}
