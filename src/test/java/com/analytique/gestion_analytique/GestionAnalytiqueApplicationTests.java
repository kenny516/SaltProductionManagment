package com.analytique.gestion_analytique;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.analytique.gestion_analytique.Models.DetailsPoste;
import com.analytique.gestion_analytique.Repositories.DetailsPosteRepository;

@SpringBootTest
class GestionAnalytiqueApplicationTests {

	@Autowired
	DetailsPosteRepository dpRepo;

	@Test
	void testCamelCase(){
		assertEquals(7, dpRepo.findAll().size());
	}

}
