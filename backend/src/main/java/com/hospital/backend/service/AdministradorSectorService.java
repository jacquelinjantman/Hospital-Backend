package com.hospital.backend.service;

import com.hospital.backend.model.*;
import com.hospital.backend.repository.AdministradorRepository;
import com.hospital.backend.repository.AdministradorSectorRepository;
import com.hospital.backend.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorSectorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private AdministradorSectorRepository administradorSectorRepository;

    public AdministradorSector asignar(Long administradorId, Long especialidadId) {
        Administrador administrador = administradorRepository.findById(administradorId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
        Especialidad especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        AdministradorSector nuevo = new AdministradorSector(administrador, especialidad);
        return administradorSectorRepository.save(nuevo);
    }
}