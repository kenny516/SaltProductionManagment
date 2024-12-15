package com.analytique.gestion_analytique.Services;

import com.analytique.gestion_analytique.Models.Conge;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repositories.CongeRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CongeService {
	private final CongeRepository repository;
	private final SoldeCongeService soldeCongeService;
	private final EmployeRepository employeRepository;

	public CongeService(CongeRepository repository, SoldeCongeService soldeCongeService,
						EmployeRepository employeRepository) {
		this.repository = repository;
		this.soldeCongeService = soldeCongeService;
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
	public void updateCongeDure(Integer id, BigDecimal duree) {
		if (repository.existsById(id)) {
			Conge existingConge = repository.findById(id).orElse(null);
			if (existingConge != null) {
				existingConge.setDuree(duree);
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
			BigDecimal duree = BigDecimal.valueOf(getBusinessDaysBetween(conge.getDateDebut(), conge.getDateFin(), null));
			conge.setDuree(duree);
			String currentStatus = conge.getStatus();
			if ((conge.getDateDebut().isBefore(LocalDate.now()) || conge.getDateDebut().isEqual(LocalDate.now()))
					&& conge.getDateFin().isAfter(LocalDate.now())) {
				conge.setStatus("En cours");
			} else if (conge.getDateFin().isBefore(LocalDate.now())) {
				conge.setStatus("Termine");
			}
			if (!currentStatus.equals(conge.getStatus())) {
				updateCongeStatus(conge.getId(), conge.getStatus());
			}
		}
	}
	public static Integer getBusinessDaysBetween(LocalDate startDate, LocalDate endDate, Set<LocalDate> holidays) {
		Integer businessDays = 0;
		LocalDate date = startDate;

		while (!date.isAfter(endDate)) {
			DayOfWeek dayOfWeek = date.getDayOfWeek();
			// Vérifie si c'est un jour ouvrable (lundi à vendredi) et pas un jour férié
			if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && (holidays == null || !holidays.contains(date))) {
				businessDays++;
			}
			date = date.plusDays(1);
		}

		return businessDays;
	}

	public List<Conge> getCongeValide() {
		List<Conge> conges = repository.findAll();
		updateStatus(conges);
        for (Conge conge : conges) {
            updateCongeDure(conge.getId(), conge.getDuree());
        }
		return conges;
	}

	public double totalCongeByEmploye(Integer idTypeConge, Integer idEmploye, Integer anneeDebut, Integer anneeFin) {
		return repository.totalCongeByEmploye(idTypeConge, idEmploye, anneeDebut, anneeFin);
	}

	public double CongePossible(Integer idTypeConge, Integer idEmploye, Integer anneeDebut, Integer anneeFin) {
		double congePossible = soldeCongeService.congePossible(idTypeConge, idEmploye, anneeDebut, anneeFin) == null ? 0
				: soldeCongeService.congePossible(idTypeConge, idEmploye, anneeDebut, anneeFin);
		double totalConge = repository.totalCongeByEmploye(idTypeConge, idEmploye, anneeDebut, anneeFin) == null ? 0
				: repository.totalCongeByEmploye(idTypeConge, idEmploye, anneeDebut, anneeFin);
		return congePossible - totalConge < 0 ? 0 : congePossible - totalConge;
	}

	public List<Conge> congeParAns(Integer idEmploye, Integer anne) {
		return repository.congeParAns(idEmploye, anne);
	}

	public Map<Integer, Integer> congeByYear(Integer idTypeConge, Integer idEmploye) {
		List<Conge> conges = repository.findByEmployeId(idTypeConge, idEmploye);
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

	public double nbrCongeDisponible(Integer idTypeConge, Integer idEmploye, Integer annee) {
		Employe employe = employeRepository.findById(idEmploye).orElse(new Employe());
		double congeDisponible = 0;
		Map<Integer, Integer> congePerYear = congeByYear(idTypeConge, idEmploye);
//		congeDisponible = (12 * 2.5) - congePerYear.get(annee);
		for(int i = employe.getDateEmbauche().getYear();i<=annee;i++){
			Double anneConge = (12*2.5);
			int an = i;
			while (anneConge>0){
				anneConge-= congePerYear.get(i);
				an++;
				if (congePerYear.get(an) == null){
					break;
				}
			}
			congeDisponible += anneConge;
		}
		return congeDisponible;
	}

	public double getNbrHeuresCongeParMois(Integer idEmploye, Integer mois, Integer annee) {
		double joursConge = repository.nbrJourCongeParMois(idEmploye, mois, annee) == null
				? 0
				: repository.nbrJourCongeParMois(idEmploye, mois, annee);

		// Convertir les jours de congé en heures (8 heures par jour)
		return joursConge * 8;
	}

	public double nbJourParMois(Integer idEmploye, Integer mois, Integer annee) {
		return repository.nbrJourCongeParMois(idEmploye, mois, annee);
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
