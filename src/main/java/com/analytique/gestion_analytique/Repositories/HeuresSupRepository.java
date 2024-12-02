package com.analytique.gestion_analytique.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.analytique.gestion_analytique.Models.HeuresSup;

public interface HeuresSupRepository extends JpaRepository<HeuresSup, Long> {

    @Query("SELECT h FROM HeuresSup h WHERE h.dateDebut >= :today")
    List<HeuresSup> findHeuresSupAfterToday(LocalDateTime today);

}
