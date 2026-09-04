package com.hospital.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name= "turno")
public class Turno{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTurno estado = EstadoTurno.PENDIENTE;

    @Column(name="motivo_consulta")
    private String motivoConsulta;

    @ManyToOne
    @JoinColumn(name= "creado_por_admin_id")
    private Administrador creadoPorAdmin;

    @ManyToOne
    @JoinColumn(name = "modificado_por_admin_id")
    private Administrador modificadoPorAdmin;

    @Column(name= "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Turno(){}

     @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Administrador getCreadoPorAdmin() {
        return creadoPorAdmin;
    }

    public void setCreadoPorAdmin(Administrador creadoPorAdmin) {
        this.creadoPorAdmin = creadoPorAdmin;
    }

    public Administrador getModificadoPorAdmin() {
        return modificadoPorAdmin;
    }

    public void setModificadoPorAdmin(Administrador modificadoPorAdmin) {
        this.modificadoPorAdmin = modificadoPorAdmin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}