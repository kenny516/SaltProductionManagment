package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class CompetencesEmployesId implements Serializable {
    private Long employer;
    private Long competence;
    
    public Long getEmployer() {
        return employer;
    }
    public void setEmployer(Long employer) {
        this.employer = employer;
    }
    public Long getCompetence() {
        return competence;
    }
    public void setCompetence(Long competence) {
        this.competence = competence;
    }
    
}

