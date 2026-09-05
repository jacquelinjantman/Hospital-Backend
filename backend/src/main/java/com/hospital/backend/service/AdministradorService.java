package com.hospital.backend.service;

import com.hospital.backend.model.Administrador;
import com.hospital.backend.repository.AdministradorRepository;
import com.hospital.backend.repository.AdministradorSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private AdministradorSectorRepository administradorSectorRepository;

    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> buscarPorId(Long id) {
        return administradorRepository.findById(id);
    }

    public Administrador guardar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public void eliminar(Long id) {
        administradorRepository.deleteById(id);
    }

    public void darDeBaja(Long administradorId) {
        Administrador administrador = administradorRepository.findById(administradorId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        boolean tieneSectoresAsignados = administradorSectorRepository.existsByAdministradorId(administradorId);

        if (tieneSectoresAsignados) {
            throw new RuntimeException(
                    "No se puede dar de baja: el administrador tiene sectores asignados. Desasignelos primero.");
        }

        administrador.setActivo(false);
        administradorRepository.save(administrador);
    }
}