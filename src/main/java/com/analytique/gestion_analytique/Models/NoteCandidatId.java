package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class NoteCandidatId implements Serializable {
    private Long candidat;
    private Long typeNote;
    
    public Long getCandidat() {
        return candidat;
    }
    public void setCandidat(Long candidat) {
        this.candidat = candidat;
    }
    public Long getTypeNote() {
        return typeNote;
    }
    public void setTypeNote(Long typeNote) {
        this.typeNote = typeNote;
    }

}

