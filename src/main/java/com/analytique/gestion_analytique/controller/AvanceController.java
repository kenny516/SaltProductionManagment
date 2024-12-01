package com.analytique.gestion_analytique.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.analytique.gestion_analytique.Models.Avance;
import com.analytique.gestion_analytique.Services.AvanceService;
import com.analytique.gestion_analytique.dto.receive.AvanceReceive;

@RestController
@RequestMapping("/api/avance")
@CrossOrigin(origins = "http://localhost:3000")
public class AvanceController {

    AvanceService avanceService;

    public AvanceController(AvanceService avanceService) {
        this.avanceService = avanceService;
    }

    @GetMapping("/intervalle-montant")
    public Map<String, BigDecimal> minMontantAvance() {
        BigDecimal min = Avance.MIN_AVANCE;
        BigDecimal max = Avance.MAX_AVANCE;

        Map<String, BigDecimal> response = new HashMap<>();
        response.put("min", min);
        response.put("max", max);

        return response;
    }

    @GetMapping("/intervalle-pourcentage")
    public Map<String, Double> rangePourcentage() {
        double min = Avance.MIN_POURCENTAGE;
        double max = Avance.MAX_POURCENTAGE;

        Map<String, Double> response = new HashMap<>();
        response.put("min", min);
        response.put("max", max);

        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AvanceReceive data) {
        return ResponseEntity.ok(avanceService.save(data));
    }
}
