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
   
    // fonction pour embaucher un candidat
    public Employe embaucher(Candidat candidat, LocalDate dateEmbauche){
        Employe emp = new Employe(candidat.getNom(), candidat.getPrenom(), candidat.getEmail(), candidat.getTelephone(), dateEmbauche, candidat.getPoste());
        return employeService.insererEmploye(emp);
    }
}
