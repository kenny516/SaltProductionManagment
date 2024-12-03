package com.analytique.gestion_analytique.controller;

import com.analytique.gestion_analytique.Models.Conge;
import com.analytique.gestion_analytique.Models.TypeConge;
import com.analytique.gestion_analytique.Services.CongeService;
import com.analytique.gestion_analytique.Services.TypeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conge")
@CrossOrigin(origins = "http://localhost:3000")
public class CongeController {

    @Autowired
    private CongeService congeService;
    @Autowired
    private TypeCongeService typeCongeService;

    @GetMapping("")
    public List<Conge> getAllConges() {
        return congeService.getCongeValide();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conge> getCongeById(@PathVariable("id") Integer id) {
        Optional<Conge> conge = Optional.ofNullable(congeService.getCongeById(id));
        return conge.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<?> createConge(@RequestBody Conge conge) {
        double jourRestant = congeService.nbrCongeDisponible(
                conge.getIdTypeConge().getId(),
                conge.getEmploye().getId(),
                conge.getDateFin().getYear()
        );
        TypeConge typeConge = typeCongeService.getTypeCongeById(conge.getIdTypeConge().getId());
        if (jourRestant < conge.getDuree().doubleValue() && typeConge.getCumulable()) {
            String errorMessage = String.format(
                    "Erreur : jours restants (%.2f) insuffisants pour accorder un congé de %.2f jours.",
                    jourRestant,
                    conge.getDuree().doubleValue()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        Conge createdConge = congeService.createConge(conge);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConge);
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

    @GetMapping("/totalCongeByEmploye/{idTypeConge}/{idEmploye}/{anne}")
    public double totalCongeByEmploye(@PathVariable("idTypeConge")Integer idTypeConge,@PathVariable("idEmploye") Integer idEmploye, @PathVariable("anne") Integer anne) {
        return congeService.totalCongeByEmploye(idTypeConge,idEmploye, anne-3,anne);
    }

}
