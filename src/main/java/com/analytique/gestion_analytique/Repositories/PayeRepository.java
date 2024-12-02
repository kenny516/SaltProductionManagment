package com.analytique.gestion_analytique.Repositories;
import com.analytique.gestion_analytique.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PayeRepository extends JpaRepository<Paye, Integer> {
    @Query(value="SELECT * FROM PAYE WHERE id_employe = :idEmploye", nativeQuery = true)
    List<Paye> getByIdEmploye(@Param("idEmploye") Integer idEmploye);

    
}
