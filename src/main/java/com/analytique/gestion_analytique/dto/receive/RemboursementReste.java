package com.analytique.gestion_analytique.dto.receive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RemboursementReste(
        Integer id,
        Integer idEmploye,
        BigDecimal montant,
        BigDecimal pourcentageDebitable,
        LocalDate dateAvance,
        String raison,
        BigDecimal restePaye,
        String nom,
        String prenom) {

}
