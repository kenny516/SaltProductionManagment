package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "typeconge")
public class TypeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 20)
    private String nom;

    @Column(name = "est_paye", nullable = false)
    private Boolean estPaye = false;
    @Column(name = "cumulable", nullable = false)
    private Boolean cumulable = false;

    @Column(name = "duree_max", precision = 4, scale = 2)
    private BigDecimal dureeMax;



}