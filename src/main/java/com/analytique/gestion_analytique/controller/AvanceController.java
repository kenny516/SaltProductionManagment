package com.analytique.gestion_analytique.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.analytique.gestion_analytique.Models.Avance;
import com.analytique.gestion_analytique.Models.AvanceRemboursement;
import com.analytique.gestion_analytique.Repositories.AvanceRemboursementRepository;
import com.analytique.gestion_analytique.Services.AvanceService;
import com.analytique.gestion_analytique.dto.receive.AvanceReceive;
import com.analytique.gestion_analytique.dto.receive.RemboursementReste;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/avance")
@CrossOrigin(origins = "http://localhost:3000")
public class AvanceController {
    AvanceRemboursementRepository avanceRemboursementRepository;
    AvanceService avanceService;

    public AvanceController(AvanceRemboursementRepository avanceRemboursementRepository, AvanceService avanceService) {
        this.avanceRemboursementRepository = avanceRemboursementRepository;
        this.avanceService = avanceService;
    }

    @GetMapping("")
    public List<RemboursementReste> getAllUnpaid() {
        return avanceService.getAllUnpaid();
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
    public Map<String, Object> save(@RequestBody AvanceReceive data) {
        Map<String, Object> response = new HashMap<>();

        try {
            Avance a = avanceService.save(data);
            response.put("avance", a);
            response.put("success", true);
        } catch (IllegalArgumentException iae) {
            response.put("success", false);
            response.put("errorMessage", iae.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return response;
    }

    
    

}
