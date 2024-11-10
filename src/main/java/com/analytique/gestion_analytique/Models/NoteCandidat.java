package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "noteCandidat")
@IdClass(NoteCandidatId.class)
public class NoteCandidat {
    @Id
    @ManyToOne
    @JoinColumn(name = "idCandidat")
    private Candidat candidat;

    @Id
    @ManyToOne
    @JoinColumn(name = "idTypeNote")
    private TypeNote typeNote;

    private Integer note;

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public TypeNote getTypeNote() {
        return typeNote;
    }

    public void setTypeNote(TypeNote typeNote) {
        this.typeNote = typeNote;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

}
