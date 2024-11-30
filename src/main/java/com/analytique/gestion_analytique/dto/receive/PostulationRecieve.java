package com.analytique.gestion_analytique.dto.receive;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.OffreEmploi;
import com.analytique.gestion_analytique.Repositories.CandidatRepository;
import com.analytique.gestion_analytique.Repositories.OffreEmploiRepository;
import com.analytique.gestion_analytique.dto.CompetenceUser;

import jakarta.persistence.EntityManager;

public class PostulationRecieve {
	int candidat_id,postulation_id;	
	LocalDate candidatureTime;
	List<CompetenceUser> competences;

	public Candidat extractCandidat(CandidatRepository cR) {
		Candidat c = cR.findById(candidat_id).get();
		return c;
	}

	public OffreEmploi extractOffreEmploi(OffreEmploiRepository cP) {
		OffreEmploi p = cP.findById(postulation_id).get();
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

	public int getCandidat_id() {
		return candidat_id;
	}

	public void setCandidat_id(int idCandidat) {
		this.candidat_id = idCandidat;
	}

	public int getPoste_id() {
		return postulation_id;
	}

	public void setPoste_id(int idPoste) {
		this.postulation_id = idPoste;
	}

	public LocalDate getCandidatureTime() {
		return candidatureTime;
	}

	public void setCandidatureTime(LocalDate candidatureTime) {
		this.candidatureTime = candidatureTime;
	}

}
