package com.analytique.gestion_analytique.Repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RuptureContratRepo extends JpaRepository<RuptureContratRepo, Integer> {

	@Query(value = "select indemnite_verse from v_rupture_contrat_actuel where id_employe = id_employe", nativeQuery = true)
	public BigDecimal findrupture(@Param("id_employe") Integer idEmploye);
}
