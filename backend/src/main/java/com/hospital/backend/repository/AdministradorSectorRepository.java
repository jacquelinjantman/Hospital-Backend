package com.hospital.backend.repository;

import com.hospital.backend.model.AdministradorSector;
import com.hospital.backend.model.AdministradorSectorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorSectorRepository extends JpaRepository<AdministradorSector, AdministradorSectorId> {
    boolean existsByAdministradorId(Long administradorId);
}