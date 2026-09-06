package com.hospital.backend.service;

import com.hospital.backend.model.*;
import com.hospital.backend.repository.DoctorRepository;
import com.hospital.backend.repository.DoctorEspecialidadRepository;
import com.hospital.backend.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorEspecialidadService {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private DoctorEspecialidadRepository  doctorEspecialidadRepository;

    public DoctorEspecialidad asignar (Long doctorId, Long especialidadId)
    {
        Doctor doctor = doctorRepository.findById(doctorId)
        .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
        Especialidad especialidad = especialidadRepository.findById(especialidadId)
            .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

            DoctorEspecialidad nuevo = new DoctorEspecialidad(doctor, especialidad);
            return doctorEspecialidadRepository.save(nuevo);
    }
}
