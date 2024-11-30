package com.analytique.gestion_analytique.Services;

import com.analytique.gestion_analytique.Models.Conge;
import com.analytique.gestion_analytique.Repositories.CongeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CongeService {
    private final CongeRepository repository;

    public CongeService(CongeRepository repository) {
        this.repository = repository;
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

    public void deleteConge(Integer id) {
        repository.deleteById(id);
    }
    // utils
    public void updateStatus(List<Conge> conges) {
        for (Conge conge : conges) {
            String currentStatus = conge.getStatus();
            if (conge.getDateDebut().isBefore(LocalDate.now()) && conge.getDateFin().isAfter(LocalDate.now())) {
                conge.setStatus("En cours");
            } else if (conge.getDateFin().isBefore(LocalDate.now())) {
                conge.setStatus("Termine");
            }
            if (!currentStatus.equals(conge.getStatus())) {
                repository.save(conge);
            }
        }
    }
}
