package com.analytique.gestion_analytique.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Paye;
import com.analytique.gestion_analytique.Repositories.PayeRepository;

@Service
public class PayeService {
    private final PayeRepository payeRepository;

    public PayeService(PayeRepository payeRepository){
        this.payeRepository = payeRepository;
    }

    public List<Paye> getAll(){
        return payeRepository.findAll();
    }
}
