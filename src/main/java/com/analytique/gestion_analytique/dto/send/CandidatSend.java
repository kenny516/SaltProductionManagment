package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;

public class CandidatSend extends Candidat {
	public CandidatSend(Candidat c) {
		setId(c.getId());
		setNom(c.getNom());
		setPrenom(c.getPrenom());
		setEmail(c.getEmail());
		setTelephone(c.getTelephone());
		setExperiences(c.getExperiences());
		setFormations(c.getFormations());
		setDiplomes(c.getDiplomes());
		setPostulations(c.getPostulations());
	}

	public CandidatSend() {
	}

}
