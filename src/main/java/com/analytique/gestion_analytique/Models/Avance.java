package com.analytique.gestion_analytique.Models;

import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "avances")
public class Avance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_employe", nullable = false)
    private Employe employe;

    private BigDecimal montant;

    @Column(name = "date_avance", nullable = false)
    private LocalDate dateAvance;

    @Column(name = "raison", length = 255)
    private String raison;

    @Column(name = "pourcentage_debitable")
    private BigDecimal pourcentageDebitable;

    @Transient
    public static final double MIN_POURCENTAGE = 10;

    @Transient
    public static final double MAX_POURCENTAGE = 30;

    @Transient
    public static final BigDecimal MIN_AVANCE = new BigDecimal("1000");

    @Transient
    public static final BigDecimal MAX_AVANCE = new BigDecimal("3500000");

}