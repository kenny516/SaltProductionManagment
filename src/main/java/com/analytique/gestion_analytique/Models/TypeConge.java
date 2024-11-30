package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "typeconge")
public class TypeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "typeconge_id_gen")
    @SequenceGenerator(name = "typeconge_id_gen", sequenceName = "typeconge_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 20)
    private String nom;

    @Column(name = "est_paye", nullable = false)
    private Boolean estPaye = false;

    @Column(name = "duree_max", precision = 4, scale = 2)
    private BigDecimal dureeMax;

    @OneToMany(mappedBy = "idTypeConge")
    private Set<Conge> conges = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTypeConge")
    private Set<SoldeConge> soldeconges = new LinkedHashSet<>();

}