package com.hospital.backend.service;

import com.hospital.backend.model.Doctor;
import com.hospital.backend.model.EstadoTurno;
import com.hospital.backend.repository.DoctorRepository;
import com.hospital.backend.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    public List<Doctor> listarTodos() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> buscarPorId(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor guardar(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void eliminar(Long id) {
        doctorRepository.deleteById(id);
    }

    public void darDeBaja(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));

        boolean tieneTurnosPendientes = turnoRepository
            .existsByDoctorIdAndEstadoIn(doctorId, List.of(EstadoTurno.PENDIENTE, EstadoTurno.CONFIRMADO));

        if (tieneTurnosPendientes) {
            throw new RuntimeException("No se puede dar de baja: el doctor tiene turnos pendientes o confirmados");
        }

        doctor.setActivo(false);
        doctorRepository.save(doctor);
    }
}
