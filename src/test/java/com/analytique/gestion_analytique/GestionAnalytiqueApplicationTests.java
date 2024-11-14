package com.analytique.gestion_analytique;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.analytique.gestion_analytique.Repositories.DetailsPosteRepository;
import com.analytique.gestion_analytique.Services.CandidatService;
import com.analytique.gestion_analytique.dto.CompetenceUser;
import com.analytique.gestion_analytique.dto.receive.CandidatRecieve;


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
