package com.hospital.backend.repository;

import com.hospital.backend.model.DisponibilidadDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface DisponibilidadDoctorRepository extends JpaRepository<DisponibilidadDoctor, Long> {

    List<DisponibilidadDoctor> findByDoctorIdAndDiaSemana(Long doctorId, DayOfWeek diaSemana);
}
