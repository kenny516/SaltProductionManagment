package com.analytique.gestion_analytique.controller;

import com.analytique.gestion_analytique.Models.TypeConge;
import com.analytique.gestion_analytique.Services.TypeCongeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-conge")
public class TypeCongeController {
    private final TypeCongeService service;

    public TypeCongeController(TypeCongeService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<TypeConge> getAllTypeConges() {
        return service.getAllTypeConges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeConge> getTypeCongeById(@PathVariable Integer id) {
        TypeConge typeConge = service.getTypeCongeById(id);
        return typeConge != null ? ResponseEntity.ok(typeConge) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public TypeConge createTypeConge(@RequestBody TypeConge typeConge) {
        return service.createTypeConge(typeConge);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeConge> updateTypeConge(@PathVariable Integer id, @RequestBody TypeConge updatedTypeConge) {
        TypeConge typeConge = service.updateTypeConge(id, updatedTypeConge);
        return typeConge != null ? ResponseEntity.ok(typeConge) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeConge(@PathVariable Integer id) {
        service.deleteTypeConge(id);
        return ResponseEntity.noContent().build();
    }
}
