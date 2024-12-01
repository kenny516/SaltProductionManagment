package com.analytique.gestion_analytique.Services;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Avance;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repositories.AvanceRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.dto.receive.AvanceReceive;

@Service
public class AvanceService {

    EmployeRepository employeRepository;
    AvanceRepository avanceRepository;

    public AvanceService(EmployeRepository employeRepository, AvanceRepository avanceRepository) {
        this.employeRepository = employeRepository;
        this.avanceRepository = avanceRepository;
    }

    public Avance save(AvanceReceive a) {
        Employe e = employeRepository.getReferenceById(a.idEmploye());

        Avance avance = new Avance();
        avance.setEmploye(e);
        avance.setDateAvance(a.dateAvance());
        avance.setPourcentageDebitable(a.pourcentageDebitable());
        avance.setRaison(a.raison());
        avance.setMontant(a.montant());

        return avanceRepository.save(avance);
    }
}
