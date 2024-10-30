package com.analytique.gestion_analytique.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.database.entity.PartsParCentre;
import java.sql.Date;


public interface PartsParCentreRepo extends JpaRepository<PartsParCentre,Long>{
	@Query("SELECT p FROM PartsParCentre p WHERE p.idCentre = :idCentre AND p.idRubrique = :idRubrique AND p.dateInsertion = (SELECT MAX(dateInsertion) FROM PartsParCentre WHERE idCentre = :idCentre AND idRubrique = :idRubrique)")
	public PartsParCentre findMostRecentByCentreAndRubrique(@Param("idCentre") Long idCentre, @Param("idRubrique") Long idRubrique);

	@Query(value = "select * from PartsParCentre WHERE idRubrique = :idRubrique group by idCentre having dateInsertion = max(dateInsertion)", nativeQuery = true)
	public List<PartsParCentre> findMostRecentByRubrique(@Param("idRubrique") Long idRubrique);

	@Query(value = "select * from PartsParCentre WHERE idRubrique = :idRubrique AND dateInsertion < :endDate group by idCentre having dateInsertion = max(dateInsertion)", nativeQuery = true)
	public List<PartsParCentre> findMostRecentByRubriqueAndEndDate(@Param("idRubrique") Long idRubrique, @Param("endDate") Date endDate);
}