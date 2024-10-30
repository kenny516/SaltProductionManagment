package com.analytique.gestion_analytique.database.entity.association;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "assodepensesparts")
public class AssoDepensesParts {
  @EmbeddedId
	private AssoDepensePartsId id;

	public AssoDepensePartsId getId() {
		return id;
	}

	public void setId(AssoDepensePartsId id) {
		this.id = id;
	}

	public Integer getIdDepense() {
		return getId().getIdDepense();
	}

	public void setIdDepense(Integer idDepense) {
		if (getId() == null) {
			initId();
		}
		this.getId().setIdDepense(idDepense);
	}

	public Integer getIdPart() {
		return this.getIdPart();
	}

	public void setIdPart(Integer idPart) {
		if (getId() == null) {
			initId();
		}
		this.getId().setIdPart(idPart);
	}

	public void initId(){
		setId(new AssoDepensePartsId());
	}

}
