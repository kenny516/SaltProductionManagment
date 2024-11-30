package com.analytique.gestion_analytique.Services;

import com.analytique.gestion_analytique.Models.SoldeConge;
import com.analytique.gestion_analytique.Repositories.SoldeCongeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldeCongeService {
    private final SoldeCongeRepository repository;

    public SoldeCongeService(SoldeCongeRepository repository) {
        this.repository = repository;
    }

    public List<SoldeConge> getAllSoldeConges() {
        return repository.findAll();
    }

    public SoldeConge getSoldeCongeById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public SoldeConge createSoldeConge(SoldeConge soldeConge) {
        return repository.save(soldeConge);
    }

    public void deleteSoldeConge(Integer id) {
        repository.deleteById(id);
    }

    // utils
    public Double congePossible(Integer idTypeConge,Integer idEmploye, Integer anneeDebut, Integer anneeFin) {
        return repository.congePossible(idTypeConge,idEmploye, anneeDebut, anneeFin);
    }
}
