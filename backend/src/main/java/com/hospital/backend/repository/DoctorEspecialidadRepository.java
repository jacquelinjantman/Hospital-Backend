package com.hospital.backend.repository;

import com.hospital.backend.model.DoctorEspecialidad;
import com.hospital.backend.model.DoctorEspecialidadId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorEspecialidadRepository extends JpaRepository<DoctorEspecialidad, DoctorEspecialidadId> {
}