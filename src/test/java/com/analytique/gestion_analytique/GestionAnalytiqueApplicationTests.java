package com.analytique.gestion_analytique;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.analytique.gestion_analytique.Models.Conge;
import com.analytique.gestion_analytique.Services.CongeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.analytique.gestion_analytique.Models.Avance;
import com.analytique.gestion_analytique.Repositories.DetailsPosteRepository;
import com.analytique.gestion_analytique.Services.AvanceService;
import com.analytique.gestion_analytique.Services.CandidatService;
import com.analytique.gestion_analytique.dto.receive.AvanceReceive;

import java.util.List;
import java.util.Map;


@SpringBootTest
class GestionAnalytiqueApplicationTests {

	@Autowired
	DetailsPosteRepository dpRepo;
	@Autowired
	CandidatService cs ;
	@Autowired
	CongeService congeService;

	@Autowired
	AvanceService as;

<<<<<<< HEAD
	@Test
	void insertionCandidat(){
		List<Conge> conges = congeService.getCongeValide();
		Map<Integer,Integer> d = congeService.congeByYear(1,1);
		System.out.println(congeService.nbrCongeDisponible(1,1,2022));
		d.forEach((year, days) -> System.out.println("Année " + year + ": " + days + " jours de congé"));
		//cs.intsertNote(1, 1, 10);
//		double nbrJour = congeService.nbJourParMois(4,12,2024);
//		System.out.println(nbrJour);
=======
	// @Test
	// void testCamelCase(){
	// 	assertEquals(7, dpRepo.findAll().size());
	// }
>>>>>>> main

	// @Test
	// void insertionCandidat(){
	// 	cs.intsertNote(1, 1, 10);

	// }

}
