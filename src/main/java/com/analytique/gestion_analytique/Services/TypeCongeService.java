package com.analytique.gestion_analytique.Services;

import com.analytique.gestion_analytique.Models.TypeConge;
import com.analytique.gestion_analytique.Repositories.TypeCongeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCongeService {
    private final TypeCongeRepository repository;

    public TypeCongeService(TypeCongeRepository repository) {
        this.repository = repository;
    }

    public List<TypeConge> getAllTypeConges() {
        return repository.findAll();
    }

    public TypeConge getTypeCongeById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public TypeConge createTypeConge(TypeConge typeConge) {
        return repository.save(typeConge);
    }

    public TypeConge updateTypeConge(Integer id, TypeConge updatedTypeConge) {
        if (repository.existsById(id)) {
            updatedTypeConge.setId(id);
            return repository.save(updatedTypeConge);
        }
        return null;
    }

    public void deleteTypeConge(Integer id) {
        repository.deleteById(id);
    }
}
