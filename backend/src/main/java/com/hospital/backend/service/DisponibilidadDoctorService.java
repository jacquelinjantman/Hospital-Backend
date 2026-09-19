package com.hospital.backend.service;

import com.hospital.backend.model.DisponibilidadDoctor;
import com.hospital.backend.repository.DisponibilidadDoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadDoctorService {

    private final DisponibilidadDoctorRepository disponibilidadRepository;

    public DisponibilidadDoctorService(DisponibilidadDoctorRepository disponibilidadDoctorRepository) {
        this.disponibilidadRepository = disponibilidadDoctorRepository;
    }

    public List<DisponibilidadDoctor> listarTodos() {
        return disponibilidadRepository.findAll();
    }

    public DisponibilidadDoctor guardar(DisponibilidadDoctor disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    public void eliminar(Long id) {
        disponibilidadRepository.deleteById(id);
    }
}
