package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class ContratEmployeId implements Serializable {
    private Integer contrat;
    private Integer employe;

    public Integer getContrat() {
        return contrat;
    }
    public void setContrat(Integer contrat) {
        this.contrat = contrat;
    }
    public Integer getEmploye() {
        return employe;
    }
    public void setEmploye(Integer employe) {
        this.employe = employe;
    }

}
