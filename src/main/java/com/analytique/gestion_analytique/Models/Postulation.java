package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Postulations")
public class Postulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "candidat_id", nullable = false)
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "Offre_emploi_id", nullable = false)
    private OffreEmploi offre;

    private String status = "En attente";

    private LocalDate datePostulation = LocalDate.now();

    @OneToMany(mappedBy = "postulation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoteCandidat> notes = new ArrayList<>();

    @OneToMany(mappedBy = "postulation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompetencesCandidats> competencesCandidats;

    public Postulation(Candidat candidat, OffreEmploi offre, LocalDate datePostulation) {
        this.candidat = candidat;
        this.offre = offre;
        this.datePostulation = datePostulation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public OffreEmploi getOffreEmploi() {
        return offre;
    }

    public void setOffreEmploi(OffreEmploi poste) {
        this.offre = poste;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDatePostulation() {
        return datePostulation;
    }

    public void setDatePostulation(LocalDate datePostulation) {
        this.datePostulation = datePostulation;
    }

		public Postulation() {
		}

        public OffreEmploi getOffre() {
            return offre;
        }

        public void setOffre(OffreEmploi offre) {
            this.offre = offre;
        }

        public List<NoteCandidat> getNotes() {
            return notes;
        }

        public void setNotes(List<NoteCandidat> notes) {
            this.notes = notes;
        }

		

}

