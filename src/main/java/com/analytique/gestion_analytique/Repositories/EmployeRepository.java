package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.Models.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long> {

}
