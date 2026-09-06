package com.hospital.backend.controller;

import com.hospital.backend.model.DoctorEspecialidad;
import com.hospital.backend.service.DoctorEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/doctors/{doctorId}/especialidades")

public class DoctorEspecialidadController {

    @Autowired
    private DoctorEspecialidadService doctorEspecialidadService;

    @PostMapping("/{especialidadId}")
    public ResponseEntity<DoctorEspecialidad> asignar(@PathVariable Long doctorId, @PathVariable Long especialidad) {
        return ResponseEntity.ok(doctorEspecialidadService.asignar(doctorId, especialidad));
    }
}
