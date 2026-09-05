package com.hospital.backend.service;

import com.hospital.backend.model.Enfermero;
import com.hospital.backend.repository.EnfermeroRepository;
import com.hospital.backend.repository.EnfermeroSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnfermeroService {

    @Autowired
    private EnfermeroRepository enfermeroRepository;

    @Autowired
    private EnfermeroSectorRepository enfermeroSectorRepository;

    public List<Enfermero> listarTodos() {
        return enfermeroRepository.findAll();
    }

    public Optional<Enfermero> buscarPorId(Long id) {
        return enfermeroRepository.findById(id);
    }

    public Enfermero guardar(Enfermero enfermero) {
        return enfermeroRepository.save(enfermero);
    }

    public void eliminar(Long id) {
        enfermeroRepository.deleteById(id);
    }

    public void darDeBaja(Long enfermeroId) {
        Enfermero enfermero = enfermeroRepository.findById(enfermeroId)
            .orElseThrow(() -> new RuntimeException("Enfermero no encontrado"));

        boolean tieneSectoresAsignados = enfermeroSectorRepository.existsByEnfermeroId(enfermeroId);

        if (tieneSectoresAsignados) {
            throw new RuntimeException("No se puede dar de baja: el enfermero tiene sectores asignados. Desasignelos primero.");
        }

        enfermero.setActivo(false);
        enfermeroRepository.save(enfermero);
    }
}