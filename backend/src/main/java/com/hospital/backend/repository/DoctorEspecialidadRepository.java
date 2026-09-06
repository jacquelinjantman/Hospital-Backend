package com.hospital.backend.repository;

import com.hospital.backend.model.DoctorEspecialidad;
import com.hospital.backend.model.DoctorEspecialidadId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorEspecialidadRepository extends JpaRepository<DoctorEspecialidad, DoctorEspecialidadId> {
}