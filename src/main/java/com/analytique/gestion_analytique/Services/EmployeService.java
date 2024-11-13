package com.analytique.gestion_analytique.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Repositories.CompetenceRepository;
import com.analytique.gestion_analytique.Repositories.ContratEmployeRepository;
import com.analytique.gestion_analytique.Repositories.EmployeRepository;
import com.analytique.gestion_analytique.dto.send.EmployeData;

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

	public List<EmployeData> getQualifiedEmployeesForPost(Integer posteId) {
		return employeRepository.findQualifiedEmployeesForPost(posteId)
				.stream()
				.map(e -> EmployeData.map(e, competenceRepository.findByEmploye(e.getId()),
						contratEmployeRepository.findByMaxDateAndEmployeId(e.getId())))
				.toList();
	}

	public Employe insererEmploye(Employe emp) {
		return employeRepository.save(emp);
	}

	public List<EmployeData> getAll() {
		return employeRepository
				.findAll()
				.stream()
				.map(e -> EmployeData.map(e, competenceRepository.findByEmploye(e.getId()),
						contratEmployeRepository.findByMaxDateAndEmployeId(e.getId())))
				.toList();
	}

	public Optional<EmployeData> getOne(int id) {
		return employeRepository
				.findById(id)
				.map(e -> EmployeData.map(e, competenceRepository.findByEmploye(e.getId()),
						contratEmployeRepository.findByMaxDateAndEmployeId(e.getId())));
	}
}
