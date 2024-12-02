package com.analytique.gestion_analytique.Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.analytique.gestion_analytique.Models.Paye;
import com.analytique.gestion_analytique.Models.AvanceRemboursement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Poste;
import com.analytique.gestion_analytique.Models.TypeContrat;
import com.analytique.gestion_analytique.Models.ContratEmploye;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Models.HeuresSup;
import com.analytique.gestion_analytique.Repositories.AvanceRemboursementRepository;
import com.analytique.gestion_analytique.Repositories.AvanceRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.ContratEmployeRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.Repositories.PayeRepository;
import com.analytique.gestion_analytique.dto.receive.RemboursementReste;
import com.analytique.gestion_analytique.dto.send.EmployeSend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.analytique.gestion_analytique.Repositories.HeuresSupRepository;
@Service
public class EmployeService {
	private final HeuresSupRepository heuresSupRepository;
	@PersistenceContext
	EntityManager entityManager;
	private final EmployeRepository employeRepository;
	private final CompetenceRepository competenceRepository;
	private final ContratEmployeRepository contratEmployeRepository;
	private final AvanceRepository avanceRepository;
	private final AvanceRemboursementRepository avanceRemboursementRepository;
	private final PayeRepository payeRepository;
	JdbcTemplate jdbcTemplate;

	public EmployeService(EmployeRepository employeRepository, CompetenceRepository competenceRepository,
			ContratEmployeRepository contratEmployeRepository, AvanceRepository avanceRepository,
			AvanceRemboursementRepository avanceRemboursementRepository, JdbcTemplate jdbcTemplate) {
		this.employeRepository = employeRepository;
		this.competenceRepository = competenceRepository;
		this.contratEmployeRepository = contratEmployeRepository;
		this.avanceRepository = avanceRepository;
		this.avanceRemboursementRepository = avanceRemboursementRepository;
		this.jdbcTemplate = jdbcTemplate;
		this.payeRepository = payeRepository;
		this.heuresSupRepository = heuresSupRepository;
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

	public ContratEmploye modifierContrat(Integer idEmploye, LocalDate date_debut, Integer contrat, Integer poste,BigDecimal nouveauSalaire){
		TypeContrat tc = contrat == null ? null :entityManager.getReference(TypeContrat.class,contrat );
		Poste p = poste == null ? null : entityManager.getReference(Poste.class, poste);

		return modifierContrat(idEmploye, date_debut, tc, p, nouveauSalaire);
	}

	@Transactional
	public ContratEmploye modifierContrat(Integer idEmploye, LocalDate date_debut, TypeContrat contrat, Poste poste,BigDecimal nouveauSalaire){
		Employe e = getOne(idEmploye).get();
		ContratEmploye nouveauContratEmploye = e.getContrat().modify(date_debut, contrat, poste, nouveauSalaire);

		entityManager.persist(e.getContrat());
		nouveauContratEmploye = contratEmployeRepository.save(nouveauContratEmploye);
		return nouveauContratEmploye;
	}


	public void validerPaiement(Integer id_employe, int mois, int annee)throws Exception{
		Paye paye = employeRepository.getPaye(mois, annee, id_employe);
		if(paye != null){
			throw new Exception("Cet employe a deja ete paye");
		}
	}
	public Paye payer(Integer IdEmploye, LocalDate datePaiement, Double heureNormale) throws Exception{
		try{
			validerPaiement(IdEmploye, datePaiement.getMonthValue(), datePaiement.getYear());
			AvanceRemboursement ar = remboursementMensuel(IdEmploye, datePaiement);
			BigDecimal totalAvance = (ar != null) ? ar.getMontant() : BigDecimal.ZERO;
			
			List<HeuresSup> heuresSups = heuresSupRepository.findByEmployeAndMonthAndYear(Long.valueOf(IdEmploye), datePaiement.getMonthValue(), datePaiement.getYear());
			Double montantHeureSup = heuresSups.stream().map(HeuresSup::getMontant).filter(montant -> montant != null).reduce(0.0,Double::sum);
			double totalHeureSup = heuresSups.stream().map(HeuresSup::getTotalHeuresSup).filter(heureSup -> heureSup != null).reduce(0.0,Double::sum);
			BigDecimal salaireBase = contratEmployeRepository.findByMaxDateAndEmployeId(IdEmploye).getSalaire();
			Double totalSalaire = salaireBase.subtract(totalAvance).doubleValue() + montantHeureSup;

			Paye paye = new Paye(null, employeRepository.getReferenceById(IdEmploye), datePaiement.getMonthValue(), datePaiement.getYear(), BigDecimal.valueOf(heureNormale), BigDecimal.valueOf(totalHeureSup), totalAvance, salaireBase, BigDecimal.valueOf(totalSalaire));

			// System.out.println(paye);
			return payeRepository.save(paye);
		}
		catch (Exception e){
			throw e;
		}
	}
	
    public Employe getEmployeById(Integer id) {
        Optional<Employe> employe = employeRepository.findById(id);
        return employe.orElseThrow(() -> new RuntimeException("Employé introuvable pour l'id: " + id));
    }

	public List<Employe> getAllEmp() {
        List<Employe> employes = employeRepository.findAll();
        return employes;
    }
}
