package com.analytique.gestion_analytique.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.HeuresSup;
import com.analytique.gestion_analytique.Services.HeuresSupService;

@RestController
@RequestMapping("/api/heures-sup")
@CrossOrigin(origins = "http://localhost:3000")
public class HeuresSupController {

    @Autowired
    private HeuresSupService heuresSupService;

    @PostMapping
    public ResponseEntity<?> creerHeuresSup(@RequestBody HeuresSup heureSup) {
        System.out.println("Requête reçue avec les données : " + heureSup);
        HeuresSup nouvelleHeureSup = heuresSupService.creerHeureSup(heureSup);
        if (nouvelleHeureSup!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleHeureSup);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erreur : Vous dépassez les 20 heures supplémentaires autorisées par semaine.");
        }
    }

    @GetMapping("/after-today")
    public List<HeuresSup> getHeuresSupAfterToday() {
        List<HeuresSup> heuresSup = heuresSupService.getHeuresSupAfterToday();
        return heuresSup;
    }
}
