package com.analytique.gestion_analytique;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.analytique.gestion_analytique.Models.ContratEmploye;
import com.analytique.gestion_analytique.Services.EmployeService;

import jakarta.transaction.Transactional;

@SpringBootTest
class GestionAnalytiqueApplicationTests {
	
	@Autowired
	EmployeService es;

	@Test
	@Transactional
	public void modificationContrat(){
		ContratEmploye ce = es.modifierContrat(1, LocalDate.parse("2025-01-01"), 1, 2, new BigDecimal(20000000));
		// assertEquals(4, ce.getId());

		ce = es.modifierContrat(2, null, 1, 2, new BigDecimal(20000000));
		// assertEquals(5, ce.getId());

		ce = es.modifierContrat(3, null, null, 3, null);
		// assertEquals(6, ce.getId());
	}

}
