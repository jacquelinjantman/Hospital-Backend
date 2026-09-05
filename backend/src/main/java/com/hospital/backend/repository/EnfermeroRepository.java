package com.hospital.backend.repository;

import com.hospital.backend.model.Enfermero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermeroRepository extends JpaRepository<Enfermero, Long> {
}