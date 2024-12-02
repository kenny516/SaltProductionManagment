package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "type_bonus_salaire")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeBonusSalaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", length = 30, nullable = false)
    private String nom;

}
