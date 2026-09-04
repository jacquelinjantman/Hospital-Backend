package com.hospital.backend.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DoctorEspecialidadId implements Serializable {

    private Long doctorId;
    private Long especialidadId;

    public DoctorEspecialidadId() {}

    public DoctorEspecialidadId(Long doctorId, Long especialidadId) {
        this.doctorId = doctorId;
        this.especialidadId = especialidadId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Long especialidadId) {
        this.especialidadId = especialidadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorEspecialidadId that = (DoctorEspecialidadId) o;
        return Objects.equals(doctorId, that.doctorId) &&
               Objects.equals(especialidadId, that.especialidadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, especialidadId);
    }
}