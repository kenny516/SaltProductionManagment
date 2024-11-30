package com.analytique.gestion_analytique.controller;

import com.analytique.gestion_analytique.Models.Conge;
import com.analytique.gestion_analytique.Services.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conge")
public class CongeController {

    @Autowired
    private CongeService congeService;

    @GetMapping("/")
    public List<Conge> getAllConges() {
        return congeService.getCongeValide();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conge> getCongeById(@PathVariable("id") Integer id) {
        Optional<Conge> conge = Optional.ofNullable(congeService.getCongeById(id));
        return conge.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Conge> createConge(@RequestBody Conge conge) {
        Conge createdConge = congeService.createConge(conge);
        return new ResponseEntity<>(createdConge, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conge> updateConge(@PathVariable("id") Integer id, @RequestBody Conge conge) {
        conge.setId(id);
        Conge updatedConge = congeService.createConge(conge);
        return ResponseEntity.ok(updatedConge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConge(@PathVariable("id") Integer id) {
        congeService.deleteConge(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/totalCongeByEmploye/{idEmploye}/{anne}")
    public double totalCongeByEmploye(@PathVariable("idEmploye") Integer idEmploye, @PathVariable("anne") Integer anne) {
        return congeService.totalCongeByEmploye(idEmploye, 0,anne);
    }

}
