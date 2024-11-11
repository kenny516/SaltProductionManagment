package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Contratemploye")
@IdClass(ContratEmployeId.class)
public class ContratEmploye {
    @Id
    @ManyToOne
    @JoinColumn(name = "idemploye")
    private Employe employe;

    @Id
    @ManyToOne
    @JoinColumn(name = "idcontrat")
    private TypeContrat contrat;

		@Column(name = "datedebut")
    private LocalDate dateDebut;
		@Column(name = "datefin")
		private LocalDate dateFin;
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    public TypeContrat getContrat() {
        return contrat;
    }
    public void setContrat(TypeContrat contrat) {
        this.contrat = contrat;
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

