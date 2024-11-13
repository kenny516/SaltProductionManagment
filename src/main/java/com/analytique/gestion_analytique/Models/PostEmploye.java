package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Postemploye")
public class PostEmploye {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idemploye")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "idposte")
    private Poste poste;

    @Column(name = "datedebut")
    private LocalDate dateDebut;

    @Column(name = "datefin")
    private LocalDate dateFin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
