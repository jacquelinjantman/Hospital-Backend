package com.hospital.backend.repository;

import com.hospital.backend.model.EstadoTurno;
import com.hospital.backend.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    boolean existsByDoctorIdAndEstadoIn(Long doctorId, List<EstadoTurno> estados);

    boolean existsByDoctorIdAndFechaHora(Long doctorId, java.time.LocalDateTime fechaHora);
}
