package com.analytique.gestion_analytique.Services;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Models.PayeDetails;
import com.analytique.gestion_analytique.dto.PayeDetailsDto;

@Service
public class PayeDetailsService {
    public static PayeDetailsDto toDTO(PayeDetails payeDetails) {
        PayeDetailsDto dto = new PayeDetailsDto();
        
        dto.setId(payeDetails.getId());
        dto.setMois(payeDetails.getMois());
        dto.setAnnee(payeDetails.getAnnee());
        dto.setHeureNormale(payeDetails.getHeureNormale());
        dto.setHeureSup(payeDetails.getHeureSup());
        dto.setSalaireBase(payeDetails.getSalaireBase());
        dto.setAvance(payeDetails.getAvance());
        dto.setTotal(payeDetails.getTotal());

        // Map fields from Employe if required
        Employe employe = payeDetails.getEmploye();
        if (employe != null) {
            dto.setIdEmploye(employe.getId());
        }

        return dto;
    }
}
