package com.hospital.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enfermero")
public class Enfermero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", unique = true, nullable = false)
    private Usuario usuario;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(unique = true, nullable = false)
    private String matricula;

    public Enfermero() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}