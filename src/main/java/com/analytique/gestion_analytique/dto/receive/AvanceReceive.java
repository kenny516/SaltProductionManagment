package com.analytique.gestion_analytique.dto.receive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AvanceReceive(
        Integer idEmploye,
        BigDecimal montant,
        LocalDate dateAvance,
        String raison,
        BigDecimal pourcentageDebitable) {
}
