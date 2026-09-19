package com.hospital.backend.controller;

import com.hospital.backend.model.DoctorEspecialidad;
import com.hospital.backend.service.DoctorEspecialidadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/doctors/{doctorId}/especialidades")

public class DoctorEspecialidadController {

    private final DoctorEspecialidadService doctorEspecialidadService;

    public DoctorEspecialidadController(DoctorEspecialidadService doctorEspecialidadService) {
        this.doctorEspecialidadService = doctorEspecialidadService;
    }

    @PostMapping("/{especialidadId}")
    public ResponseEntity<DoctorEspecialidad> asignar(@PathVariable Long doctorId, @PathVariable Long especialidad) {
        return ResponseEntity.ok(doctorEspecialidadService.asignar(doctorId, especialidad));
    }
}
