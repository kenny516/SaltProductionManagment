package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class CompetencesCandidatsId implements Serializable {
    private Integer candidat;
    private Integer competence;

    public Integer getCandidat() {
        return candidat;
    }
    public void setCandidat(Integer candidat) {
        this.candidat = candidat;
    }
    public Integer getCompetence() {
        return competence;
    }
    public void setCompetence(Integer competence) {
        this.competence = competence;
    }
    
}

