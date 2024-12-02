package com.analytique.gestion_analytique.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.ContratEmploye;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.ContratEmployeRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.dto.send.EmployeSend;

@Service
public class EmployeService {
	private final EmployeRepository employeRepository;
	private final CompetenceRepository competenceRepository;
	private final ContratEmployeRepository contratEmployeRepository;

	public EmployeService(EmployeRepository employeRepository, CompetenceRepository competenceRepository,
			ContratEmployeRepository contratEmployeRepository) {
		this.employeRepository = employeRepository;
		this.competenceRepository = competenceRepository;
		this.contratEmployeRepository = contratEmployeRepository;
	}

	public List<EmployeSend> getQualifiedEmployeesForPost(Integer posteId) {
		return employeRepository.findQualifiedEmployeesForPost(posteId)
				.stream()
				.map(e -> EmployeSend.map(e, competenceRepository.findByEmploye(e.getId())))
				.toList();
	}

	public Employe insererEmploye(Employe emp) {
		return employeRepository.save(emp);
	}

	public List<EmployeSend> getAll() {
		return employeRepository
				.findAll()
				.stream()
				.map(e -> EmployeSend.map(e, competenceRepository.findByEmploye(e.getId())))
				.toList();
	}

	public Optional<EmployeSend> getOne(int id) {
		return employeRepository
				.findById(id)
				.map(e -> EmployeSend.map(e, competenceRepository.findByEmploye(e.getId())));
	}

    public Employe getEmployeById(Integer id) {
        Optional<Employe> employe = employeRepository.findById(id);
        return employe.orElseThrow(() -> new RuntimeException("Employé introuvable pour l'id: " + id));
    }

	public List<Employe> getAllEmp() {
        List<Employe> employes = employeRepository.findAll();
        return employes;
    }
}
