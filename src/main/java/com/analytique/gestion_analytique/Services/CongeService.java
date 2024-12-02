package com.analytique.gestion_analytique.Services;

import com.analytique.gestion_analytique.Models.Conge;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repositories.CongeRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
public class CongeService {
    private final CongeRepository repository;
    private final SoldeCongeService soldeCongeService;
    private final EmployeRepository employeRepository;
        private EmployeService employeService;
    
        public CongeService(CongeRepository repository, SoldeCongeService soldeCongeService, EmployeService employeService, EmployeRepository employeRepository) {
            this.repository = repository;
            this.soldeCongeService = soldeCongeService;
            this.employeService = employeService;
            this.employeRepository = employeRepository;
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
                if ((conge.getDateDebut().isBefore(LocalDate.now()) || conge.getDateDebut().isEqual(LocalDate.now())) && conge.getDateFin().isAfter(LocalDate.now())) {
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
    
        public double totalCongeByEmploye(Integer idTypeConge,Integer idEmploye, Integer anneeDebut, Integer anneeFin) {
            return repository.totalCongeByEmploye(idTypeConge,idEmploye, anneeDebut, anneeFin);
        }
    
    
        public double CongePossible(Integer idTypeConge,Integer idEmploye, Integer anneeDebut, Integer anneeFin) {
            double congePossible = soldeCongeService.congePossible(idTypeConge,idEmploye, anneeDebut, anneeFin) == null ? 0 : soldeCongeService.congePossible(idTypeConge,idEmploye, anneeDebut, anneeFin);
            double totalConge = repository.totalCongeByEmploye(idTypeConge,idEmploye, anneeDebut, anneeFin) == null ? 0 : repository.totalCongeByEmploye(idTypeConge,idEmploye, anneeDebut, anneeFin);
            return congePossible - totalConge < 0 ? 0 : congePossible - totalConge;
        }
    
        public List<Conge> congeParAns(Integer idEmploye, Integer anne) {
            return repository.congeParAns(idEmploye, anne);
        }
    
    
        public double nbrCongeDisponible(Integer idTypeConge,Integer idEmploye, Integer annee) {
            double congeDisponible = 0;
            Map<Integer, Integer> congePerYear = congeByYear(idTypeConge,idEmploye);
            congeDisponible = (12*2.5) - congePerYear.get(annee);
            return congeDisponible;
        }
    
        public double getNbrHeuresCongeParMois(Integer idEmploye, Integer mois, Integer annee) {
            double joursConge = repository.nbrJourCongeParMois(idEmploye, mois, annee) == null 
                ? 0 
                : repository.nbrJourCongeParMois(idEmploye, mois, annee);
            
            // Convertir les jours de congé en heures (8 heures par jour)
            return joursConge * 8;
        }    
    
        public double getNbrHeuresCongeNonPayeParMois(Integer idEmploye, Integer mois, Integer annee) {
            Double joursCongeNonPaye = repository.nbrJourCongeNonPayeParMois(idEmploye, mois, annee) == null
                ? 0
                : repository.nbrJourCongeNonPayeParMois(idEmploye, mois, annee);
        
            // Convertir les jours de congé non payés en heures (8 heures par jour)
            return joursCongeNonPaye * 8;
        }
        
        public double getMontantDroitConge(Integer idEmploye, Integer mois, Integer annee) {
            Employe employe = employeRepository.getReferenceById(idEmploye);
        
        if (employe == null || employe.getContrat().getTauxHoraire() == null) {
            throw new IllegalArgumentException("L'employé ou son taux horaire est introuvable.");
        }

        double tauxHoraire = employe.getContrat().getTauxHoraire().doubleValue();
    
        double heuresCongesPayes = getNbrHeuresCongeNonPayeParMois(idEmploye, mois, annee);
    
        return heuresCongesPayes * tauxHoraire;
    }
}
