package com.hospital.backend.repository;

import com.hospital.backend.model.Enfermero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfermeroRepository extends JpaRepository<Enfermero, Long> {
}