package com.analytique.gestion_analytique.Services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.analytique.gestion_analytique.Models.Paye;
import com.analytique.gestion_analytique.Models.PayeDetails;
import com.analytique.gestion_analytique.Models.AvanceRemboursement;
import com.analytique.gestion_analytique.Models.BonusSalaire;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Models.HeuresSup;
import com.analytique.gestion_analytique.Repositories.AvanceRemboursementRepository;
import com.analytique.gestion_analytique.Repositories.AvanceRepository;
import com.analytique.gestion_analytique.Repositories.BonusSalaireRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.ContratEmployeRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.dto.receive.RemboursementReste;
import com.analytique.gestion_analytique.dto.send.EmployeSend;
import com.analytique.gestion_analytique.Repositories.HeuresSupRepository;
import com.analytique.gestion_analytique.Repositories.PayeDetailsRepository;
import com.analytique.gestion_analytique.Repositories.PayeRepository;
@Service
public class EmployeService {
	public final BonusSalaireRepository bonusSalaireRepository;
	private final HeuresSupRepository heuresSupRepository;
	private final EmployeRepository employeRepository;
	private final CompetenceRepository competenceRepository;
	private final ContratEmployeRepository contratEmployeRepository;
	private final AvanceRepository avanceRepository;
	private final AvanceRemboursementRepository avanceRemboursementRepository;
	private final PayeRepository payeRepository;
	public final PayeDetailsRepository payeDetailsRepository;
	JdbcTemplate jdbcTemplate;

	public EmployeService(EmployeRepository employeRepository, CompetenceRepository competenceRepository,
			ContratEmployeRepository contratEmployeRepository, AvanceRepository avanceRepository,
			AvanceRemboursementRepository avanceRemboursementRepository, JdbcTemplate jdbcTemplate, PayeRepository payeRepository, HeuresSupRepository heuresSupRepository, BonusSalaireRepository bonusSalaireRepository, PayeDetailsRepository payeDetailsRepository) {
		this.employeRepository = employeRepository;
		this.competenceRepository = competenceRepository;
		this.contratEmployeRepository = contratEmployeRepository;
		this.avanceRepository = avanceRepository;
		this.avanceRemboursementRepository = avanceRemboursementRepository;
		this.jdbcTemplate = jdbcTemplate;
		this.payeRepository = payeRepository;
		this.heuresSupRepository = heuresSupRepository;
		this.bonusSalaireRepository = bonusSalaireRepository;
		this.payeDetailsRepository = payeDetailsRepository;
	}

	public List<EmployeSend> getQualifiedEmployeesForPost(Integer posteId) {
		return employeRepository.findQualifiedEmployeesForPost(posteId)
				.stream()
				.map(e -> EmployeSend.map(e, competenceRepository.findByEmploye(e.getId())))
				.toList();
	}

	public Employe insererEmploye(Employe emp) {
		return employeRepository.save(emp);
	}

	public List<EmployeSend> getAll() {
		return employeRepository
				.findAll()
				.stream()
				.map(e -> EmployeSend.map(e, competenceRepository.findByEmploye(e.getId())))
				.toList();
	}

	public Optional<EmployeSend> getOne(int id) {
		return employeRepository
				.findById(id)
				.map(e -> EmployeSend.map(e, competenceRepository.findByEmploye(e.getId())));
	}

	public List<RemboursementReste> getAllAvances(Integer idEmploye, Boolean unpaid) {
		String sql = "SELECT * FROM v_avances_avec_reste WHERE id_employe = ?";
		if (unpaid != null) {
			if (unpaid) {
				sql += " AND reste_payer > 0";
			} else {
				sql += " AND (reste_payer IS NULL OR reste_payer = 0)";
			}
		}
		return jdbcTemplate.query(sql, rs -> {
			List<RemboursementReste> result = new ArrayList<>();
			while (rs.next()) {
				result.add(new RemboursementReste(
						rs.getInt("id"),
						rs.getInt("id_employe"),
						rs.getBigDecimal("montant"),
						rs.getBigDecimal("pourcentage_debitable"),
						rs.getDate("date_avance").toLocalDate(),
						rs.getString("raison"),
						rs.getBigDecimal("reste_payer"),
						null,
						null));
			}
			return result;
		}, idEmploye);
	}

	public RemboursementReste getDernierImpaye(Integer idEmploye) {
		List<RemboursementReste> result = getAllAvances(idEmploye, true);
		if (result == null || result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public BigDecimal getRemboursementMensuel(RemboursementReste rr) {
		Employe e = employeRepository.getReferenceById(rr.idEmploye());
		BigDecimal salaire = e.getContrat().getSalaire();

		return salaire.multiply(rr.pourcentageDebitable()).divide(BigDecimal.valueOf(100));
	}

	public AvanceRemboursement remboursementMensuel(Integer idEmploye, LocalDate dateRemboursement) {
		RemboursementReste rr = getDernierImpaye(idEmploye);

		if (rr != null) {
			BigDecimal aPayer = getRemboursementMensuel(rr);

			AvanceRemboursement ar = new AvanceRemboursement();
			ar.setAvance(avanceRepository.getReferenceById(rr.id()));
			ar.setDateRemboursement(dateRemboursement);
			ar.setMontant(aPayer);

			return avanceRemboursementRepository.save(ar);
		}

		return null;
	}

	public void controlerPaiement(Integer id_employe, int mois, int annee)throws Exception{
		Paye paye = employeRepository.getPaye(mois, annee, id_employe);
		if(paye != null){
			throw new Exception("Cet employe a deja ete paye");
		}
	}
	// public Paye payer(Integer IdEmploye, LocalDate datePaiement, Double heureNormale) throws Exception{
	// 	try{
	// 		controlerPaiement(IdEmploye, datePaiement.getMonthValue(), datePaiement.getYear());
	// 		AvanceRemboursement ar = remboursementMensuel(IdEmploye, datePaiement);
	// 		BigDecimal totalAvance = (ar != null) ? ar.getMontant() : BigDecimal.ZERO;
			
	// 		List<HeuresSup> heuresSups = heuresSupRepository.findByEmployeAndMonthAndYear(Long.valueOf(IdEmploye), datePaiement.getMonthValue(), datePaiement.getYear());
	// 		Double montantHeureSup = heuresSups.stream().map(HeuresSup::getMontant).filter(montant -> montant != null).reduce(0.0,Double::sum);
	// 		double totalHeureSup = heuresSups.stream().map(HeuresSup::getTotalHeuresSup).filter(heureSup -> heureSup != null).reduce(0.0,Double::sum);
	// 		BigDecimal salaireBase = contratEmployeRepository.findByMaxDateAndEmployeId(IdEmploye).getSalaire();
	// 		Double totalSalaire = salaireBase.subtract(totalAvance).doubleValue() + montantHeureSup;

	// 		Paye paye = new Paye(null, employeRepository.getReferenceById(IdEmploye), datePaiement.getMonthValue(), datePaiement.getYear(), BigDecimal.valueOf(heureNormale), BigDecimal.valueOf(totalHeureSup), totalAvance, salaireBase, BigDecimal.valueOf(totalSalaire));

	// 		// System.out.println(paye);
	// 		return payeRepository.save(paye);
	// 	}
	// 	catch (Exception e){
	// 		throw e;
	// 	}
	// }

	public Double calculerIrsa(Integer idEmploye){
		return employeRepository.calculerIrsa(idEmploye);
	}

	public BigDecimal getPrime(Integer idEmploye, int mois, int annee){
		BigDecimal prime = (bonusSalaireRepository.getPrimeByMonthAndYear(mois, annee, idEmploye)!=null) ?bonusSalaireRepository.getPrimeByMonthAndYear(mois, annee, idEmploye).stream().map(BonusSalaire::getMontant).filter(montant -> montant != null).reduce(BigDecimal.ZERO,BigDecimal::add) : BigDecimal.ZERO;
		return prime;
	}
	public BigDecimal getIndemnite(Integer idEmploye, int mois, int annee){
		BigDecimal indemnite = (bonusSalaireRepository.getIndemniteByMonthAndYear(mois, annee, idEmploye)!=null) ?bonusSalaireRepository.getIndemniteByMonthAndYear(mois, annee, idEmploye).stream().map(BonusSalaire::getMontant).filter(montant -> montant != null).reduce(BigDecimal.ZERO,BigDecimal::add) : BigDecimal.ZERO;
		return indemnite;
	}

	public BigDecimal calculerCNAPS(BigDecimal salaireBase){
		// 1.500.000 ar ny plafond pour cnaps
		BigDecimal baseCalcul = salaireBase.min(BigDecimal.valueOf(1500000));
        return baseCalcul.multiply(BigDecimal.valueOf(0.01))
                         .setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal calculerSanitaire(BigDecimal salaireBase){
        return salaireBase.multiply(BigDecimal.valueOf(0.01));
	}

	public PayeDetails validerPaiement(Integer IdEmploye, LocalDate datePaiement, Double heureNormale) throws Exception{
		try {
			controlerPaiement(IdEmploye,datePaiement.getMonthValue(), datePaiement.getYear());
			AvanceRemboursement ar = remboursementMensuel(IdEmploye, datePaiement);
			BigDecimal totalAvance = (ar != null) ? ar.getMontant() : BigDecimal.ZERO;
			List<HeuresSup> heuresSups = heuresSupRepository.findByEmployeAndMonthAndYear(Long.valueOf(IdEmploye), datePaiement.getMonthValue(), datePaiement.getYear());
			Double montantHeureSup = heuresSups.stream().map(HeuresSup::getMontant).filter(montant -> montant != null).reduce(0.0,Double::sum);
			double totalHeureSup = heuresSups.stream().map(HeuresSup::getTotalHeuresSup).filter(heureSup -> heureSup != null).reduce(0.0,Double::sum);
			BigDecimal salaireBase = contratEmployeRepository.findByMaxDateAndEmployeId(IdEmploye).getSalaire();
			// Double totalSalaire = salaireBase.subtract(totalAvance).doubleValue() + montantHeureSup;

			// TODO: prendre nb heure conge 
			BigDecimal nbHeureAbsence = BigDecimal.ZERO;
			// TODO: prendre mmontant preavis
			BigDecimal droitPreavis = BigDecimal.ZERO;
			// TODO: prendre montant a deduire du salaire pour les conges non payé
			BigDecimal droitConge = BigDecimal.ZERO;

			BigDecimal indemnite = getIndemnite(IdEmploye,datePaiement.getMonthValue(), datePaiement.getYear());
			BigDecimal prime = getPrime(IdEmploye,datePaiement.getMonthValue(), datePaiement.getYear());
			BigDecimal irsa = BigDecimal.valueOf(calculerIrsa(IdEmploye));
			BigDecimal cnaps = calculerCNAPS(salaireBase);
			BigDecimal sanitaire = calculerSanitaire(salaireBase);

			BigDecimal total = salaireBase.add(BigDecimal.valueOf(montantHeureSup)).add(prime).add(indemnite).subtract(totalAvance).subtract(droitConge).add(droitPreavis).subtract(irsa).subtract(cnaps).subtract(sanitaire);

			PayeDetails payeDetails = new PayeDetails(employeRepository.getReferenceById(IdEmploye), Integer.valueOf(datePaiement.getMonthValue()), Integer.valueOf(datePaiement.getYear()), BigDecimal.valueOf(heureNormale), BigDecimal.valueOf(totalHeureSup), BigDecimal.valueOf(montantHeureSup), salaireBase, totalAvance, nbHeureAbsence, droitConge, droitPreavis, indemnite, prime, irsa, cnaps, sanitaire, total);

			Paye paye = new Paye(null, employeRepository.getReferenceById(IdEmploye), datePaiement.getMonthValue(), datePaiement.getYear(), BigDecimal.valueOf(heureNormale), BigDecimal.valueOf(totalHeureSup), totalAvance, salaireBase, total);
			payeRepository.save(paye);

			return payeDetailsRepository.save(payeDetails);

		} catch (Exception e) {
			throw e;
		}
	}
	public List<Paye> getPayeByIdEmploye(Integer idEmploye){
		return payeRepository.getByIdEmploye(idEmploye);
	}
	
}
