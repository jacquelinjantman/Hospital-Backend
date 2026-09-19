package com.hospital.backend.service;

import com.hospital.backend.model.*;
import com.hospital.backend.repository.DoctorRepository;
import com.hospital.backend.repository.DoctorEspecialidadRepository;
import com.hospital.backend.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorEspecialidadService {

    private final DoctorRepository doctorRepository;

    private final EspecialidadRepository especialidadRepository;

    private final DoctorEspecialidadRepository doctorEspecialidadRepository;

    public DoctorEspecialidadService(DoctorRepository doctorRepository,
            EspecialidadRepository especialidadRepository,
            DoctorEspecialidadRepository doctorEspecialidadRepository) {
        this.doctorRepository = doctorRepository;
        this.especialidadRepository = especialidadRepository;
        this.doctorEspecialidadRepository = doctorEspecialidadRepository;
    }

    public DoctorEspecialidad asignar(Long doctorId, Long especialidadId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
        Especialidad especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        DoctorEspecialidad nuevo = new DoctorEspecialidad(doctor, especialidad);
        return doctorEspecialidadRepository.save(nuevo);
    }
}
