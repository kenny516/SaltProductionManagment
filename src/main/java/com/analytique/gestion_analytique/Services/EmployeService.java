package com.analytique.gestion_analytique.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repository.EmployeRepository;

@Service
public class EmployeService {
    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public List<Employe> getQualifiedEmployeesForPost(Long posteId) {
        return employeRepository.findQualifiedEmployeesForPost(posteId);
    }
}
