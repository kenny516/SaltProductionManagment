package com.analytique.gestion_analytique.dto.fromDatabase;

import java.util.List;

import com.analytique.gestion_analytique.database.entity.rubrique.PartsParCentre;
import com.analytique.gestion_analytique.database.entity.rubrique.Rubrique;

import jakarta.persistence.EntityManager;

public class PPCentreDTO extends PartsParCentre {
	CentreDTO centre;
	Rubrique rubrique;

	public CentreDTO getCentre() {
		return centre;
	}

	public void setCentre(CentreDTO centre) {
		this.centre = centre;
	}

	public Rubrique getRubrique() {
		return rubrique;
	}

	public void setRubrique(Rubrique rubrique) {
		this.rubrique = rubrique;
	}

	public PPCentreDTO() {
		super();
	}

	public PPCentreDTO(PartsParCentre ppc, EntityManager em) {
		this.setId(ppc.getId());
		this.setIdRubrique(ppc.getIdRubrique());
		this.setIdCentre(ppc.getIdCentre());
		this.setValeur(ppc.getValeur());
		this.setDateInsertion(ppc.getDateInsertion());
		if (em != null) {
			this.setCentre(new CentreDTO(ppc.getIdCentre(), em));
			this.setRubrique(em.find(Rubrique.class, ppc.getIdRubrique()));
		}
	}

	public static List<PPCentreDTO> map(List<PartsParCentre> parCentres, EntityManager em){
		return parCentres.stream().map(r -> new PPCentreDTO(r, em)).toList();
	}  
}
