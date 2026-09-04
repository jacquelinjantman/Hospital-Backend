package com.hospital.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador_sector")
public class AdministradorSector {

    @EmbeddedId
    private AdministradorSectorId id;

    @ManyToOne
    @MapsId("administradorId")
    @JoinColumn(name = "administrador_id")
    private Administrador administrador;

    @ManyToOne
    @MapsId("especialidadId")
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    public AdministradorSector() {}

    public AdministradorSector(Administrador administrador, Especialidad especialidad) {
        this.administrador = administrador;
        this.especialidad = especialidad;
        this.id = new AdministradorSectorId(administrador.getId(), especialidad.getId());
    }

    public AdministradorSectorId getId(){
        return id;
    }
 
    public void setId(AdministradorSectorId id){
        this.id = id;
    }

    public Administrador getAdministrador(){
        return administrador;
    }

    public void setAdministrador(Administrador administrador){
        this.administrador = administrador;
    }

    public Especialidad getEspecialidad(){
       return especialidad;
    }

    public void setEspecialidad( Especialidad especialidad){
        this.especialidad = especialidad;
    }

}
