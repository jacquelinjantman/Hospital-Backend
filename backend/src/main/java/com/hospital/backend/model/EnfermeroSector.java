package com.hospital.backend.model;

import jakarta.persistence.*;
@Entity
@Table(name = "enfermero_sector")
public class EnfermeroSector {
    
    @EmbeddedId
    private EnfermeroSectorId id;


    @ManyToOne
    @MapsId("enfermeroId")
    @JoinColumn(name = "enfermero_id")
    private Enfermero enfermero;

    @ManyToOne
    @MapsId("especialidadId")
    @JoinColumn (name = "especialidad_id")
    private Especialidad especialidad;

    public EnfermeroSector(){}

    public EnfermeroSector(Enfermero enfermero, Especialidad especialidad)
    {
        this.enfermero = enfermero;
        this.especialidad = especialidad;
        this.id = new EnfermeroSectorId(enfermero.getId(), especialidad.getId());

    }

    public EnfermeroSectorId getId(){
        return id;
    }

    public void setId(EnfermeroSectorId id){
        this.id = id;
    }

    public Enfermero getEnfermero(){
        return enfermero;
    }

    public void setEnfermero(Enfermero enfermero){
        this.enfermero = enfermero;
    }

    public Especialidad getEspecialidad(){
        return especialidad;
    }

    public void setEspecialidad( Especialidad especialidad)
    {
        this.especialidad = especialidad;
    }

}
