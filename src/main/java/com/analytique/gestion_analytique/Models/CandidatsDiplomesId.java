package com.analytique.gestion_analytique.Models;

import java.io.Serializable;
import java.util.Objects;

public class CandidatsDiplomesId implements Serializable {
    private Integer candidat; // Use the type of the ID in the Candidat entity
    private Integer diplome;  // Use the type of the ID in the Diplome entity

    // Default constructor
    public CandidatsDiplomesId() {
    }

    public CandidatsDiplomesId(Integer candidat, Integer diplome) {
        this.candidat = candidat;
        this.diplome = diplome;
    }

    // Getters and setters
    public Integer getCandidat() {
        return candidat;
    }

    public void setCandidat(Integer candidat) {
        this.candidat = candidat;
    }

    public Integer getDiplome() {
        return diplome;
    }

    public void setDiplome(Integer diplome) {
        this.diplome = diplome;
    }

    // Override equals and hashCode for correct behavior
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatsDiplomesId that = (CandidatsDiplomesId) o;
        return Objects.equals(candidat, that.candidat) && Objects.equals(diplome, that.diplome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidat, diplome);
    }
}

