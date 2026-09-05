package com.hospital.backend.controller;

import com.hospital.backend.model.EnfermeroSector;
import com.hospital.backend.service.EnfermeroSectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enfermeros/{enfermeroId}/sectores")
public class EnfermeroSectorController {

    @Autowired
    private EnfermeroSectorService enfermeroSectorService;

    @PostMapping("/{especialidadId}")
    public ResponseEntity<EnfermeroSector> asignar(@PathVariable Long enfermeroId, @PathVariable Long especialidadId) {
        return ResponseEntity.ok(enfermeroSectorService.asignar(enfermeroId, especialidadId));
    }
}