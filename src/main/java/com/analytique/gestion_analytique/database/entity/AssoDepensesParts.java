package com.analytique.gestion_analytique.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AssoDepensesParts")
public class AssoDepensesParts {
  
  @Id
  @Column(name = "idDepense")
  private Long idDepense;
  
  @Id
  @Column(name = "idPart")
  private Long idPart;
  
  
  public Long getIdDepense() {
    return idDepense;
  }
  
  public void setIdDepense(Long idDepense) {
    this.idDepense = idDepense;
  }
  
  public Long getIdPart() {
    return idPart;
  }
  
  public void setIdPart(Long idPart) {
    this.idPart = idPart;
  }
  
}
