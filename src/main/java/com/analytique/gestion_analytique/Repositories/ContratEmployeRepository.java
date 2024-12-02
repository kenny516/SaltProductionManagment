package com.analytique.gestion_analytique.Repositories;
import com.analytique.gestion_analytique.Models.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContratEmployeRepository extends JpaRepository<ContratEmploye, Long> {

	@Query(value = "SELECT ce FROM ContratEmploye ce WHERE ce.employe.id = :employeId ORDER BY ce.dateDebut DESC")
	List<ContratEmploye> findByEmployeId(@Param("employeId") Integer employeId);

	ContratEmploye findById(Integer id);

	@Query(value = "SELECT ce FROM ContratEmploye ce WHERE ce.employe.id = :employeId AND ce.dateDebut = (SELECT MAX(c2.dateDebut) FROM ContratEmploye c2 WHERE c2.employe.id = :employeId)")
	ContratEmploye findByMaxDateAndEmployeId(@Param("employeId") Integer employeId);
} 