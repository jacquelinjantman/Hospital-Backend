package com.hospital.backend.repository;

import com.hospital.backend.model.EstadoTurno;
import com.hospital.backend.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

    boolean existsByDoctorIdAndEstadoIn(Long doctorId, List<EstadoTurno> estados);
}
