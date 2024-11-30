package com.analytique.gestion_analytique.Services;

import com.analytique.gestion_analytique.Models.Conge;
import com.analytique.gestion_analytique.Repositories.CongeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CongeService {
    private final CongeRepository repository;
    private final SoldeCongeService soldeCongeService;

    public CongeService(CongeRepository repository, SoldeCongeService soldeCongeService) {
        this.repository = repository;
        this.soldeCongeService = soldeCongeService;
    }

    public List<Conge> getAllConges() {
        return repository.findAll();
    }

    public Conge getCongeById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Conge createConge(Conge conge) {
        return repository.save(conge);
    }

    public Conge updateConge(Integer id, Conge updatedConge) {
        if (repository.existsById(id)) {
            updatedConge.setId(id);
            return repository.save(updatedConge);
        }
        return null;
    }
    public void updateCongeStatus(Integer id, String newStatus) {
        if (repository.existsById(id)) {
            Conge existingConge = repository.findById(id).orElse(null);
            if (existingConge != null) {
                existingConge.setStatus(newStatus);
                repository.save(existingConge);
            }
        }
    }



    public void deleteConge(Integer id) {
        repository.deleteById(id);
    }
    // utils
    public void updateStatus(List<Conge> conges) {
        for (Conge conge : conges) {
            BigDecimal duree = BigDecimal.valueOf(ChronoUnit.DAYS.between(conge.getDateDebut(), conge.getDateFin()) + 1);
            conge.setDuree(duree);
            String currentStatus = conge.getStatus();
            if (conge.getDateDebut().isBefore(LocalDate.now()) && conge.getDateFin().isAfter(LocalDate.now())) {
                conge.setStatus("En cours");
            } else if (conge.getDateFin().isBefore(LocalDate.now())) {
                conge.setStatus("Termine");
            }
            if (!currentStatus.equals(conge.getStatus())) {
                updateCongeStatus(conge.getId(), conge.getStatus());
            }
        }
    }

    public List<Conge> getCongeValide(){
        List<Conge> conges = repository.findAll();
        updateStatus(conges);
        return conges;
    }

    public double totalCongeByEmploye(Integer idEmploye, Integer anneDebut, Integer anneeFin) {
        return repository.totalCongeByEmploye(idEmploye, anneDebut, anneeFin);
    }
    public double CongePossible(Integer idEmploye, Integer anneeDebut, Integer anneeFin) {
        double congePossible = soldeCongeService.congePossible(idEmploye, anneeDebut, anneeFin);
        double totalConge = totalCongeByEmploye(idEmploye, anneeDebut, anneeFin);
        return congePossible - totalConge < 0 ? 0 : congePossible - totalConge;
    }

    public List<Conge> congeParAns(Integer idEmploye, Integer anne) {
        return repository.congeParAns(idEmploye, anne);
    }


}
