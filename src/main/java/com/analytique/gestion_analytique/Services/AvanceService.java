package com.analytique.gestion_analytique.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Avance;
import com.analytique.gestion_analytique.Models.AvanceRemboursement;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repositories.AvanceRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.dto.receive.AvanceReceive;

@Service
public class AvanceService {

    EmployeRepository employeRepository;
    AvanceRepository avanceRepository;
    private JdbcTemplate jdbcTemplate;

    public AvanceService(EmployeRepository employeRepository, AvanceRepository avanceRepository,
            JdbcTemplate jdbcTemplate) {
        this.employeRepository = employeRepository;
        this.avanceRepository = avanceRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Avance save(AvanceReceive a) throws IllegalArgumentException {
        Employe e = employeRepository.getReferenceById(a.idEmploye());

        // un employe n'a pas le droit de demander une avance s'il en a déjà une qui n'est pas encore payée en totalité
        BigDecimal restePayer = jdbcTemplate.query(
                "SELECT reste_payer from v_avances_impayes WHERE id_employe = ?",
                rs -> {
                    if (rs.next())
                        return rs.getBigDecimal(1);
                    return null;
                },
                e.getId());

        if (restePayer != null) {
            throw new IllegalArgumentException("Employé non éligible à une avance (reste à payer de " + restePayer + ")");
        }

        Avance avance = new Avance();
        avance.setEmploye(e);
        avance.setDateAvance(a.dateAvance());
        avance.setPourcentageDebitable(a.pourcentageDebitable());
        avance.setRaison(a.raison());
        avance.setMontant(a.montant());

        return avanceRepository.save(avance);
    }

    public List<AvanceRemboursement> getAllUnpaid() {
        String sql = "SELECT id, reste_payer from v_avances_impayes";

        return jdbcTemplate.query(
            sql,
            rs -> {
                List<AvanceRemboursement> remboursements = new ArrayList<>();

                while (rs.next()) {
                    Integer idAvance = rs.getInt(1);
                    BigDecimal montantRestant = rs.getBigDecimal(2);
                    Avance avance = avanceRepository.getReferenceById(idAvance);

                    AvanceRemboursement ar = new AvanceRemboursement();
                    ar.setAvance(avance);
                    ar.setDateRemboursement(avance.getDateAvance());
                    ar.setMontant(montantRestant);

                    remboursements.add(ar);
                }

                return remboursements;
            }
        );
    }
}
