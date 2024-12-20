package com.analytique.gestion_analytique.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.RuptureContrat;

public interface RuptureContratRepository extends JpaRepository<RuptureContrat,Integer> {

	@Query(value = "select * from v_rupture_contrat_actuel where id_employe = id_employe", nativeQuery = true)
	public RuptureContrat findrupture(@Param("id_employe") Integer idEmploye);
}
