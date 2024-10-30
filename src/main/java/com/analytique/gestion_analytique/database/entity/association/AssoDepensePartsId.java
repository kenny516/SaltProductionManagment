package com.analytique.gestion_analytique.database.entity.association;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class AssoDepensePartsId implements java.io.Serializable{
	private static final long serialVersionUID = 4280232807423004963L;

  @Column(name = "iddepense",nullable = false)
  private Integer idDepense;
  
  @Column(name = "idpart",nullable = false)
  private Integer idPart;

	public Integer getIdDepense() {
		return idDepense;
	}

	public void setIdDepense(Integer idDepense) {
		this.idDepense = idDepense;
	}

	public Integer getIdPart() {
		return idPart;
	}

	public void setIdPart(Integer idPart) {
		this.idPart = idPart;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdDepense(),getIdPart());
	}	
}
