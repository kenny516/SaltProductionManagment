package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class NoteCandidatId implements Serializable {
    private Integer postulation;
    private Integer typeNote;
    
    public Integer getpostulation() {
        return postulation;
    }
    public void setpostulation(Integer postulation) {
        this.postulation = postulation;
    }
    public Integer getTypeNote() {
        return typeNote;
    }
    public void setTypeNote(Integer typeNote) {
        this.typeNote = typeNote;
    }

	public NoteCandidatId(Integer postulation, Integer typeNote) {
		this.postulation = postulation;
		this.typeNote = typeNote;
	}

		public NoteCandidatId(){}

}

