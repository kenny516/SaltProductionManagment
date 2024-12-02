package com.analytique.gestion_analytique.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analytique.gestion_analytique.Services.EmployeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/avance")
@CrossOrigin(origins = "http://localhost:3000")
public class PayeController {
    EmployeService employeService;

    public PayeController(EmployeService employeService){
        this.employeService = employeService;
    }

  
    
}
