package com.hospital.backend.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AdministradorSectorId implements Serializable {
    
    private Long administradorId;
    private Long especialidadId;

    public AdministradorSectorId() {}

    public AdministradorSectorId(Long administradorId, Long especialidadId) {
        this.administradorId = administradorId;
        this.especialidadId = especialidadId;
    }

    public Long getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(Long administradorId) {
        this.administradorId = administradorId;
    }

    public Long getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Long especialidadId) {
        this.especialidadId = especialidadId;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministradorSectorId that = (AdministradorSectorId) o;
        return Objects.equals(administradorId, that.administradorId) &&
               Objects.equals(especialidadId, that.especialidadId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(administradorId, especialidadId);
    }
}
