package com.hospital.backend.repository;

import com.hospital.backend.model.EnfermeroSector;
import com.hospital.backend.model.EnfermeroSectorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfermeroSectorRepository extends JpaRepository<EnfermeroSector, EnfermeroSectorId> {
    boolean existsByEnfermeroId(Long enfermeroId);
}