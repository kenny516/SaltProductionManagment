package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class ContratEmployeId implements Serializable {
    private Long contrat;
    private Long employe;

    public Long getContrat() {
        return contrat;
    }
    public void setContrat(Long contrat) {
        this.contrat = contrat;
    }
    public Long getEmploye() {
        return employe;
    }
    public void setEmploye(Long employe) {
        this.employe = employe;
    }

}
