package com.analytique.gestion_analytique.Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.AvanceRemboursement;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repositories.AvanceRemboursementRepository;
import com.analytique.gestion_analytique.Repositories.AvanceRepository;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.ContratEmployeRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.dto.receive.RemboursementReste;
import com.analytique.gestion_analytique.dto.send.EmployeSend;

@Service
public class EmployeService {
	private final EmployeRepository employeRepository;
	private final CompetenceRepository competenceRepository;
	private final ContratEmployeRepository contratEmployeRepository;
	private final AvanceRepository avanceRepository;
	private final AvanceRemboursementRepository avanceRemboursementRepository;
	JdbcTemplate jdbcTemplate;

	public EmployeService(EmployeRepository employeRepository, CompetenceRepository competenceRepository,
			ContratEmployeRepository contratEmployeRepository, AvanceRepository avanceRepository, AvanceRemboursementRepository avanceRemboursementRepository, JdbcTemplate jdbcTemplate) {
		this.employeRepository = employeRepository;
		this.competenceRepository = competenceRepository;
		this.contratEmployeRepository = contratEmployeRepository;
		this.avanceRepository = avanceRepository;
		this.avanceRemboursementRepository = avanceRemboursementRepository;
		this.jdbcTemplate = jdbcTemplate;
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
						rs.getBigDecimal("reste_payer")));
			}
			return result;
		}, idEmploye);
	}

	public RemboursementReste getDernierImpaye(Integer idEmploye) {
		List<RemboursementReste> result = getAllAvances(idEmploye, true);
		if(result == null || result.size() == 0) {
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

		if(rr != null) {
			BigDecimal aPayer = getRemboursementMensuel(rr);
	
			AvanceRemboursement ar = new AvanceRemboursement();
			ar.setAvance(avanceRepository.getReferenceById(rr.id()));
			ar.setDateRemboursement(dateRemboursement);
			ar.setMontant(aPayer);
	
			return avanceRemboursementRepository.save(ar);
		}

		return null;
	}
	
}
