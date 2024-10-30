package com.analytique.gestion_analytique.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.database.entity.achat.Depenses;

import java.util.List;
import java.sql.Date;

public interface DepensesRepo extends JpaRepository<Depenses, Integer> {

	public List<Depenses> findByIdRubrique(Integer idRubrique);

	public List<Depenses> findByDateDepense(Date dateDepense);

	public List<Depenses> findByDateDepenseBetweenAndIdRubrique(Date startDate, Date endDate, Integer idRubrique);

	public List<Depenses> findByDateDepenseBetween(Date startDate, Date endDate);
}
