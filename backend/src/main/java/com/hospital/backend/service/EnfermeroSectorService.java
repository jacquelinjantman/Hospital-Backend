package com.hospital.backend.service;

import com.hospital.backend.model.*;
import com.hospital.backend.repository.EnfermeroRepository;
import com.hospital.backend.repository.EnfermeroSectorRepository;
import com.hospital.backend.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnfermeroSectorService {

    @Autowired
    private EnfermeroRepository enfermeroRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private EnfermeroSectorRepository enfermeroSectorRepository;

    public EnfermeroSector asignar(Long enfermeroId, Long especialidadId) {
        Enfermero enfermero = enfermeroRepository.findById(enfermeroId)
                .orElseThrow(() -> new RuntimeException("Enfermero no encontrado"));
        Especialidad especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        EnfermeroSector nuevo = new EnfermeroSector(enfermero, especialidad);
        return enfermeroSectorRepository.save(nuevo);
    }
}