package com.analytique.gestion_analytique.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "bonus_salaire")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonusSalaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_employe", nullable = false)
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "type_bonus", nullable = false)
    private TypeBonusSalaire typeBonusSalaire;

    @Column(name = "montant", precision = 10, scale = 2, nullable = false)
    private BigDecimal montant;

    @Column(name = "date_bonus", nullable = false)
    private LocalDate dateBonus;
}