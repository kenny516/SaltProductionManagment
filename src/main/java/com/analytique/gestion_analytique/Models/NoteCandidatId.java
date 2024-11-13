package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class NoteCandidatId implements Serializable {
    private Integer candidat;
    private Integer typeNote;
    
    public Integer getCandidat() {
        return candidat;
    }
    public void setCandidat(Integer candidat) {
        this.candidat = candidat;
    }
    public Integer getTypeNote() {
        return typeNote;
    }
    public void setTypeNote(Integer typeNote) {
        this.typeNote = typeNote;
    }

}

