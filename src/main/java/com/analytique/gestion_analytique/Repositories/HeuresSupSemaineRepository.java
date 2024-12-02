package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analytique.gestion_analytique.Models.HeuresSupSemaine;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HeuresSupSemaineRepository extends JpaRepository<HeuresSupSemaine, Long> {
    List<HeuresSupSemaine> findBySemaineAndIdEmploye( Long idEmploye, LocalDate semaine,);
}
