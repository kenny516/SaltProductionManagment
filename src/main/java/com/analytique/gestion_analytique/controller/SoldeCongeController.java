package com.analytique.gestion_analytique.controller;

import com.analytique.gestion_analytique.Models.SoldeConge;
import com.analytique.gestion_analytique.Services.SoldeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solde-conge")
public class SoldeCongeController {

    @Autowired
    private SoldeCongeService soldeCongeService;

    @GetMapping("/")
    public List<SoldeConge> getAllSoldeConges() {
        return soldeCongeService.getAllSoldeConges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoldeConge> getSoldeCongeById(@PathVariable("id") Integer id) {
        Optional<SoldeConge> soldeConge = Optional.ofNullable(soldeCongeService.getSoldeCongeById(id));
        return soldeConge.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<SoldeConge> createSoldeConge(@RequestBody SoldeConge soldeConge) {
        SoldeConge createdSoldeConge = soldeCongeService.createSoldeConge(soldeConge);
        return new ResponseEntity<>(createdSoldeConge, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoldeConge> updateSoldeConge(@PathVariable("id") Integer id, @RequestBody SoldeConge soldeConge) {
        soldeConge.setId(id);
        SoldeConge updatedSoldeConge = soldeCongeService.createSoldeConge(soldeConge);
        return ResponseEntity.ok(updatedSoldeConge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoldeConge(@PathVariable("id") Integer id) {
        soldeCongeService.deleteSoldeConge(id);
        return ResponseEntity.noContent().build();
    }
}
