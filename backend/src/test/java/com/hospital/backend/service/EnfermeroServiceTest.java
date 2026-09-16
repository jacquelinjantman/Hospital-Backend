package com.hospital.backend.service;

import com.hospital.backend.model.Enfermero;
import com.hospital.backend.repository.EnfermeroRepository;
import com.hospital.backend.repository.EnfermeroSectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnfermeroServiceTest {

    @Mock
    private EnfermeroRepository enfermeroRepository;

    @Mock
    private EnfermeroSectorRepository enfermeroSectorRepository;

    @InjectMocks
    private EnfermeroService enfermeroService;

    private Enfermero enfermero;

    @BeforeEach
    void setUp() {
        enfermero = new Enfermero();
        enfermero.setId(1L);
        enfermero.setNombreCompleto("Enfermera Test");
        enfermero.setMatricula("ENF001");
        enfermero.setActivo(true);

    }

    @Test
    void darBaja_DebeFuncionar_SiNoTieneSector() {
        when(enfermeroRepository.findById(1L)).thenReturn(Optional.of(enfermero));
        when(enfermeroSectorRepository.existsByEnfermeroId(1L)).thenReturn(false);

        enfermeroService.darDeBaja(1L);

        assertFalse(enfermero.getActivo());
        verify(enfermeroRepository).save(enfermero);
    }

    @Test
    void darDeBaja_deberiaFallar_siTieneSectoresAsignados() {
        when(enfermeroRepository.findById(1L)).thenReturn(Optional.of(enfermero));
        when(enfermeroSectorRepository.existsByEnfermeroId(1L)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            enfermeroService.darDeBaja(1L);
        });

        assertEquals(
                "No se puede dar de baja: el enfermero tiene sectores asignados. Desasignelos primero.",
                exception.getMessage());
        verify(enfermeroRepository, never()).save(any());
    }

    @Test
    void darBaja_DebeFallar_SiEnfermeroNoExiste() {
        when(enfermeroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            enfermeroService.darDeBaja(99L);
        });

    }
}
