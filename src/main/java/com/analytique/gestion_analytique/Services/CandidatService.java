package com.analytique.gestion_analytique.Services;
import com.analytique.gestion_analytique.Repositories.*;
import com.analytique.gestion_analytique.Models.*;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
@Service
public class CandidatService {
    private final CandidatRepository candidatRepository;
    private final EmployeService employeService;
    public CandidatService(CandidatRepository candidatRepository, EmployeService employeService) {
        this.candidatRepository = candidatRepository;
        this.employeService = employeService;
    }
   
    
}
