package com.analytique.gestion_analytique.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.dto.send.EmployeData;

@Service
public class EmployeService {
    private final EmployeRepository employeRepository;
		private final CompetenceRepository competenceRepository;

    public EmployeService(EmployeRepository employeRepository, CompetenceRepository competenceRepository) {
			this.employeRepository = employeRepository;
			this.competenceRepository = competenceRepository;
		}
		public List<Employe> getQualifiedEmployeesForPost(Integer posteId) {
        return employeRepository.findQualifiedEmployeesForPost(posteId);
    }
    public Employe insererEmploye(Employe emp){
        return  employeRepository.save(emp);
    }


		public List<EmployeData> getAll(){
			return employeRepository
					.findAll()
					.stream()
					.map(e -> EmployeData.map(e, competenceRepository.findByEmploye(e.getId())))
					.toList();
		}
}
