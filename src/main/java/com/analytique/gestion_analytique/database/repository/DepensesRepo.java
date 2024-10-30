package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.Depenses;
import java.util.List;
import java.sql.Date;

public interface DepensesRepo extends JpaRepository<Depenses, Long> {

	public List<Depenses> findByIdRubrique(Long idRubrique);

	public List<Depenses> findByIdCentre(Long idCentre);

	public List<Depenses> findByDateDepense(Date dateDepense);

	public List<Depenses> findByDateDepenseBetweenAndIdRubrique(Date startDate, Date endDate, Long idRubrique);

	public List<Depenses> findByDateDepenseBetween(Date startDate, Date endDate);
}
