package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class CompetencesCandidatsId implements Serializable {
    private Long candidat;
    private Long competence;

    public Long getCandidat() {
        return candidat;
    }
    public void setCandidat(Long candidat) {
        this.candidat = candidat;
    }
    public Long getCompetence() {
        return competence;
    }
    public void setCompetence(Long competence) {
        this.competence = competence;
    }
    
}

