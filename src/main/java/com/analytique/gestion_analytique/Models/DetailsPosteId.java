package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class DetailsPosteId implements Serializable {
    private Long poste;
    private Long competence;
    public Long getPoste() {
        return poste;
    }
    public void setPoste(Long poste) {
        this.poste = poste;
    }
    public Long getCompetence() {
        return competence;
    }
    public void setCompetence(Long competence) {
        this.competence = competence;
    }
}
