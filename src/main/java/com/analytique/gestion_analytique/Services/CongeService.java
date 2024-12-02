package com.analytique.gestion_analytique.Services;

import com.analytique.gestion_analytique.Models.Conge;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repositories.CongeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CongeService {
    private final CongeRepository repository;
    private final SoldeCongeService soldeCongeService;
    private final EmployeService employeService;

    public CongeService(CongeRepository repository, SoldeCongeService soldeCongeService, EmployeService employeService) {
        this.repository = repository;
        this.soldeCongeService = soldeCongeService;
        this.employeService = employeService;
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

    public List<Conge> getCongeValide() {
        List<Conge> conges = repository.findAll();
        updateStatus(conges);
        return conges;
    }

    public double totalCongeByEmploye(Integer idTypeConge, Integer idEmploye, Integer anneeDebut, Integer anneeFin) {
        return repository.totalCongeByEmploye(idTypeConge, idEmploye, anneeDebut, anneeFin);
    }


    public double CongePossible(Integer idTypeConge, Integer idEmploye, Integer anneeDebut, Integer anneeFin) {
        double congePossible = soldeCongeService.congePossible(idTypeConge, idEmploye, anneeDebut, anneeFin) == null ? 0 : soldeCongeService.congePossible(idTypeConge, idEmploye, anneeDebut, anneeFin);
        double totalConge = repository.totalCongeByEmploye(idTypeConge, idEmploye, anneeDebut, anneeFin) == null ? 0 : repository.totalCongeByEmploye(idTypeConge, idEmploye, anneeDebut, anneeFin);
        return congePossible - totalConge < 0 ? 0 : congePossible - totalConge;
    }

    public List<Conge> congeParAns(Integer idEmploye, Integer anne) {
        return repository.congeParAns(idEmploye, anne);
    }

    public double nbJourParMois(Integer idEmploye, Integer mois, Integer anne) {
        return repository.nbrJourCongeParMois(idEmploye, mois, anne) == null ? 0 : repository.nbrJourCongeParMois(idEmploye, mois, anne);
    }

    public double nbJourParMoisPaye(Integer idEmploye, Integer mois, Integer annee) {
        return repository.nbrJourCongeParMoisPaye(idEmploye, mois, annee) == null ? 0 : repository.nbrJourCongeParMoisPaye(idEmploye, mois, annee);
    }

    public Map<Integer, Integer> congeByYear(Integer idTypeConge,Integer idEmploye) {
        List<Conge> conges = repository.findByEmployeId(idTypeConge,idEmploye);
        Map<Integer, Integer> congePerYear = new HashMap<>();
        for (Conge conge : conges) {
            if (conge.getDateDebut().getYear() != conge.getDateFin().getYear()) {
                LocalDate finAnnee = LocalDate.of(conge.getDateDebut().getYear(), 12, 31);
                long nbJours = ChronoUnit.DAYS.between(conge.getDateDebut(), finAnnee) + 1;
                congePerYear.put(conge.getDateDebut().getYear(),
                        congePerYear.getOrDefault(conge.getDateDebut().getYear(), 0) + (int) nbJours);
                // date fin
                LocalDate debutAnneeSuivante = LocalDate.of(conge.getDateFin().getYear(), 1, 1);
                nbJours = ChronoUnit.DAYS.between(debutAnneeSuivante, conge.getDateFin()) + 1;
                congePerYear.put(conge.getDateFin().getYear(),
                        congePerYear.getOrDefault(conge.getDateFin().getYear(), 0) + (int) nbJours);
            } else {
                long nbJours = ChronoUnit.DAYS.between(conge.getDateDebut(), conge.getDateFin()) + 1;
                congePerYear.put(conge.getDateDebut().getYear(),
                        congePerYear.getOrDefault(conge.getDateDebut().getYear(), 0) + (int) nbJours);
            }
        }
        return congePerYear;
    }

    public double nbrCongeDisponible(Integer idTypeConge,Integer idEmploye, Integer annee) {
        double congeDisponible = 0;
        Map<Integer, Integer> congePerYear = congeByYear(idTypeConge,idEmploye);
        congeDisponible = (12*2.5) - congePerYear.get(annee);
        return congeDisponible;
    }

    public BigDecimal getMontantCongesNonPayesForMonthAndYear(Integer employeId, int month, int year, BigDecimal tauxJournalier) {
        // Définir les dates de début et de fin pour le mois et l'année spécifiés
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());

        // Récupérer tous les congés non payés pour l'employé donné
        List<Conge> congés = repository.findByEmployeIdAndIdTypeCongeEstPayeFalse(employeId);

        BigDecimal totalDuree = BigDecimal.ZERO;

        for (Conge conge : congés) {
            // Vérifier si le congé se trouve dans le mois et l'année spécifiés
            if ((conge.getDateDebut().isBefore(endOfMonth) || conge.getDateDebut().isEqual(endOfMonth)) &&
                (conge.getDateFin().isAfter(startOfMonth) || conge.getDateFin().isEqual(startOfMonth))) {
                
                // Ajouter la durée du congé au total
                totalDuree = totalDuree.add(conge.getDuree());
            }
        }

        // Calculer le montant du droit de congé
        BigDecimal montantConges = totalDuree.multiply(tauxJournalier);

        return montantConges;
    }
}
