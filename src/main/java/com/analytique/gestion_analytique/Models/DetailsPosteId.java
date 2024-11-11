package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class DetailsPosteId implements Serializable {
    private Integer poste;
    private Integer competence;
    public Integer getPoste() {
        return poste;
    }
    public void setPoste(Integer poste) {
        this.poste = poste;
    }
    public Integer getCompetence() {
        return competence;
    }
    public void setCompetence(Integer competence) {
        this.competence = competence;
    }
}
