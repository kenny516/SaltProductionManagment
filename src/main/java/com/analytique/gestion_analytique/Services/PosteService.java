package com.analytique.gestion_analytique.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.DetailsPoste;
import com.analytique.gestion_analytique.Models.Poste;
import com.analytique.gestion_analytique.Repositories.DetailsPosteRepository;
import com.analytique.gestion_analytique.Repositories.PosteRepository;
import com.analytique.gestion_analytique.dto.receive.PosteRecieve;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class PosteService {

	@PersistenceContext
	private EntityManager entityManager;
	private final PosteRepository posteRepository;
	private final DetailsPosteRepository detailsPosteRepository;

	public PosteService(PosteRepository posteRepository, DetailsPosteRepository detailsPosteRepository) {
		this.posteRepository = posteRepository;
		this.detailsPosteRepository = detailsPosteRepository;
	}
	
	public List<Poste> getAll(){
		return posteRepository.findAll();
	}

	public Poste savePoste(PosteRecieve poste){
		Poste p = posteRepository.save(poste);
		List<DetailsPoste> details = poste.extractDetails(entityManager);

		for (DetailsPoste detailsPoste : details) {
			detailsPoste.setPoste(poste);
		}
		details = detailsPosteRepository.saveAll(details);

		return p;
	}
	
}
