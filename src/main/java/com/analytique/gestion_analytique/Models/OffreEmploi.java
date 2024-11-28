package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "offre_emploi")
public class OffreEmploi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean status;

    @Column(name = "date_publication", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate datePublication;

    @ManyToOne
    @JoinColumn(name = "poste_id", nullable = false, foreignKey = @ForeignKey(name = "fk_offre_poste"))
    private Poste poste;

    @Column(name = "nbr_candidat_dm")
    private Integer nbrCandidatDm;

    // Getters and Settersz

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Integer getNbrCandidatDm() {
        return nbrCandidatDm;
    }

    public void setNbrCandidatDm(Integer nbrCandidatDm) {
        this.nbrCandidatDm = nbrCandidatDm;
    }
}

