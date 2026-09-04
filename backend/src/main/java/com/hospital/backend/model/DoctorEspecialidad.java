package com.hospital.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor_especialidad")
public class DoctorEspecialidad {
    @EmbeddedId
    private DoctorEspecialidadId id;

    @ManyToOne
    @MapsId("doctorId")
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @MapsId("especialidadId")
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    public DoctorEspecialidad() {
    }

    public DoctorEspecialidad(Doctor doctor, Especialidad especialidad) {
        this.doctor = doctor;
        this.especialidad = especialidad;
        this.id = new DoctorEspecialidadId(doctor.getId(), especialidad.getId());
    }

    public DoctorEspecialidadId getId() {
        return id;
    }

    public void setId(DoctorEspecialidadId id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}