package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Paye;
import com.analytique.gestion_analytique.Models.V_PayeEmployeDetails;
import com.analytique.gestion_analytique.Repositories.V_CandidatPostulationRepository;
import com.analytique.gestion_analytique.Repositories.V_PayeEmployeDetailsRepository;

@RestController
@RequestMapping("/api/payedetails")
@CrossOrigin(origins = "http://localhost:3000")
public class V_PayeEmployeDetailsController {
    private final V_PayeEmployeDetailsRepository v_PayeEmployeDetailsRepository;

    public V_PayeEmployeDetailsController(V_PayeEmployeDetailsRepository v_Paye){
        this.v_PayeEmployeDetailsRepository = v_Paye;
    }

    @GetMapping("/{idEmploye}")
    public V_PayeEmployeDetails getListByEmp(@PathVariable Integer idEmploye, @RequestParam(name="mois")Integer mois, @RequestParam(name = "annee")Integer annee) throws Exception {
        try {
            return v_PayeEmployeDetailsRepository.getPayeDetails(mois, annee, idEmploye);
        } catch (Exception e) {
           throw e;
        }
    }
}   
