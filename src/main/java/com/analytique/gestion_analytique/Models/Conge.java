package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "conge")
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conge_id_gen")
    @SequenceGenerator(name = "conge_id_gen", sequenceName = "conge_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employe", nullable = false)
    private Employe Employe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_type_conge", nullable = false)
    private TypeConge idTypeConge;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @ColumnDefault("((date_fin - date_debut) + 1)")
    @Column(name = "duree", precision = 5, scale = 2)
    private BigDecimal duree;

    @ColumnDefault("'En attente'")
    @Column(name = "status", length = 20)
    private String status;

}