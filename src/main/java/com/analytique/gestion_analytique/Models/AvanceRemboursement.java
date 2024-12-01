package com.analytique.gestion_analytique.Models;

import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "avanceremboursement")
public class AvanceRemboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_avance", nullable = false)
    private Avance avance;

    private BigDecimal montant;

    @Column(name = "date_remboursement", nullable = false)
    private LocalDate dateRemboursement;

}