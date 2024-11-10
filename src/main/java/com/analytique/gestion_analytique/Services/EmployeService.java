package com.analytique.gestion_analytique.Services;
import com.analytique.gestion_analytique.Repositories.*;
import com.analytique.gestion_analytique.Models.*;
import org.springframework.stereotype.Service;
@Service
public class EmployeService {
    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }
    public Employe insererEmploye(Employe emp){
        return  employeRepository.save(emp);
    }
}
