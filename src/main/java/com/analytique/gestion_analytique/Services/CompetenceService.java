package com.analytique.gestion_analytique.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;

@Service
public class CompetenceService {
	private final CompetenceRepository repo;

	public CompetenceService(CompetenceRepository repo) {
		this.repo = repo;
	}

	public List<Competence> findAll(){
		return repo.findAll();
	}

	public List<Competence> findByPoste(int poste_id){
		return repo.findByPoste(poste_id);
	}

	public List<Competence> findByEmploye(int employe_id){
		return repo.findByEmploye(employe_id);
	}
}
