package com.hospital.backend.service;

import com.hospital.backend.model.Administrador;
import com.hospital.backend.repository.AdministradorRepository;
import com.hospital.backend.repository.AdministradorSectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdministradorServiceTest {

    @Mock 
    private AdministradorRepository administradorRepository;

    @Mock 
    private AdministradorSectorRepository administradorSectorRepository;

    @InjectMocks 
    private AdministradorService administradorService;

    private Administrador administrador;

    @BeforeEach 
    void setUp(){
        administrador = new Administrador();
        administrador.setId(1L);
        administrador.setNombreCompleto("Admin Test");
        administrador.setActivo(true);
    }

    @Test 
    void darDeBaja_DebeFuncionar_SiNoTieneSectorAsignado(){
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(administradorSectorRepository.existsByAdministradorId(1L)).thenReturn(false);

        administradorService.darDeBaja(1L);

        assertFalse(administrador.getActivo());
        verify(administradorRepository).save(administrador);
    }

      @Test
    void darDeBaja_deberiaFallar_siTieneSectoresAsignados() {
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(administradorSectorRepository.existsByAdministradorId(1L)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            administradorService.darDeBaja(1L);
        });

      assertEquals(
    "No se puede dar de baja: el administrador tiene sectores asignados. Desasignelos primero.",
    exception.getMessage()
);
        verify(administradorRepository, never()).save(any());
    }

    @Test

    void darDeBaja_DebeFallar_SiElAdminNoExiste() {
        when(administradorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            administradorService.darDeBaja(99L);
        });
        }
    }





