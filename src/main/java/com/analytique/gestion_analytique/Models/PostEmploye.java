package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "PostEmploye")
public class PostEmploye {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idEmploye")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "idPoste")
    private Poste poste;

    @JoinColumn(name = "dateDebut")
    private LocalDate dateDebut;

    @Column(nullable = true)
    private LocalDate dateFin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

}
