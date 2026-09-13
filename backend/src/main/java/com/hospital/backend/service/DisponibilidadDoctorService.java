package com.hospital.backend.service;

import com.hospital.backend.model.DisponibilidadDoctor;
import com.hospital.backend.repository.DisponibilidadDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadDoctorService {

    @Autowired
    private DisponibilidadDoctorRepository disponibilidadRepository;

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
