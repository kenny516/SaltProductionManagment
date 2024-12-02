package com.analytique.gestion_analytique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Models.Paye;
import com.analytique.gestion_analytique.Services.EmployeService;
import com.analytique.gestion_analytique.Services.PayeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/paye")
@CrossOrigin(origins = "http://localhost:3000")
public class PayeController {
    @Autowired
    EmployeService employeService;
    @Autowired
    PayeService payeService;


    @GetMapping("")
    public List<Paye> getAll() {
        return payeService.getAll();
    }
    

    @GetMapping("/{idEmploye}")
    public List<Paye> getListByEmp(@PathVariable Integer idEmploye) {
        try {
            return employeService.getByIdEmploye(idEmploye);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
    
}
