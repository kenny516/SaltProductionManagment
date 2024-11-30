package com.analytique.gestion_analytique.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.HeuresSup;
import com.analytique.gestion_analytique.Services.HeuresSupService;

@RestController
@RequestMapping("/api/heures-sup")
public class HeuresSupController {

    @Autowired
    private HeuresSupService heuresSupService;

    @PostMapping
    public ResponseEntity<HeuresSup> creerHeuresSup(@RequestBody HeuresSup heureSup) {
        HeuresSup nouvelleHeureSup = heuresSupService.creerHeureSup(heureSup);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleHeureSup);
    }

    @GetMapping("/semaine/{idEmploye}")
    public ResponseEntity<List<HeuresSup>> obtenirHeuresSupParSemaine(@PathVariable Long idEmploye, @RequestParam LocalDate semaine) {
        List<HeuresSup> heuresSup = heuresSupService.getByEmployeAndWeek(idEmploye, semaine);
        return ResponseEntity.ok(heuresSup);
    }
}
