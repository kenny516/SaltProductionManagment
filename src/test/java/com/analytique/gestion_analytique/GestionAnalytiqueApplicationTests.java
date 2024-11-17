package com.analytique.gestion_analytique;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.analytique.gestion_analytique.Repositories.DetailsPosteRepository;
import com.analytique.gestion_analytique.Services.CandidatService;


@SpringBootTest
class GestionAnalytiqueApplicationTests {

	@Autowired
	DetailsPosteRepository dpRepo;
	@Autowired
	CandidatService cs ;

	@Test
	void testCamelCase(){
		assertEquals(7, dpRepo.findAll().size());
	}

	@Test
	void insertionCandidat(){
		cs.intsertNote(1, 1, 10);

	}

}
