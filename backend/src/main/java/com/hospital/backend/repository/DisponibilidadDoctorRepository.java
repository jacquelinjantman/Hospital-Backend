package com.hospital.backend.repository;

import com.hospital.backend.model.DisponibilidadDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface DisponibilidadDoctorRepository extends JpaRepository<DisponibilidadDoctor, Long> {

    List<DisponibilidadDoctor> findByDoctorIdAndDiaSemana(Long doctorId, DayOfWeek diaSemana);
}
