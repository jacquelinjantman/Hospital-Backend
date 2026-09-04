package com.hospital.backend.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnfermeroSectorId implements Serializable {

    private Long enfermeroId;
    private Long especialidadId;

    public EnfermeroSectorId() {}

    public EnfermeroSectorId(Long enfermeroId, Long especialidadId) {
        this.enfermeroId = enfermeroId;
        this.especialidadId = especialidadId;
    }

    public Long getEnfermeroId() {
        return enfermeroId;
    }

    public void setEnfermeroId(Long enfermeroId) {
        this.enfermeroId = enfermeroId;
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
        EnfermeroSectorId that = (EnfermeroSectorId) o;
        return Objects.equals(enfermeroId, that.enfermeroId) &&
               Objects.equals(especialidadId, that.especialidadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enfermeroId, especialidadId);
    }
}