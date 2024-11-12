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
import com.analytique.gestion_analytique.dto.receive.CandidatureData;
import com.analytique.gestion_analytique.dto.receive.CompetenceUser;

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
		List<CompetenceUser> list = new ArrayList<>();
		list.add(new CompetenceUser(1, 3));
		CandidatureData cd = new CandidatureData("patteer", "maner2", "mailereee", "0023", 2,LocalDateTime.now(), list);

		cs.saveCandidat(cd);

	}

}
