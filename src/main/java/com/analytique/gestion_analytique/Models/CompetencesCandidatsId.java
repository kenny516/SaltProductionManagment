package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class CompetencesCandidatsId implements Serializable {
    private Integer postulation;
    private Integer competence;

    public Integer getPostulation() {
        return postulation;
    }
    public void setPostulation(Integer postulation) {
        this.postulation = postulation;
    }
    public Integer getCompetence() {
        return competence;
    }
    public void setCompetence(Integer competence) {
        this.competence = competence;
    }
    
}

