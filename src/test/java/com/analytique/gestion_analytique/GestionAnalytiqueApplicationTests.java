package com.analytique.gestion_analytique;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.analytique.gestion_analytique.Repositories.DetailsPosteRepository;
import com.analytique.gestion_analytique.Services.CandidatService;
import com.analytique.gestion_analytique.dto.receive.CandidatRecieve;
import com.analytique.gestion_analytique.dto.receive.CompetenceUser;

@SpringBootTest
class GestionAnalytiqueApplicationTests {

	@Autowired
	DetailsPosteRepository dpRepo;
	@Autowired
	CandidatService cs ;

	

}
