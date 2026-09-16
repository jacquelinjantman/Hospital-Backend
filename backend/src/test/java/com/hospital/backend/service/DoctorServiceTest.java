package com.hospital.backend.service;

import com.hospital.backend.model.Doctor;
import com.hospital.backend.model.EstadoTurno;
import com.hospital.backend.repository.DoctorRepository;
import com.hospital.backend.repository.TurnoRepository;
import com.hospital.backend.service.DoctorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private TurnoRepository turnoRepository;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setNombreCompleto("Dr.Test");
        doctor.setMatricula("MP0001");
        doctor.setActivo(true);
    }

    @Test
    void DarBaja_TienequeFuncionar_SinoTieneTurnos() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(turnoRepository.existsByDoctorIdAndEstadoIn(eq(1L), anyList())).thenReturn(false);

        doctorService.darDeBaja(1L);

        assertFalse(doctor.getActivo());
        verify(doctorRepository).save(doctor);
    }

    @Test
    void darBaja_DebeFallar_SiTieneTurno() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(turnoRepository.existsByDoctorIdAndEstadoIn(eq(1L), anyList())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            doctorService.darDeBaja(1L);
        });

        assertEquals("No se puede dar de baja: el doctor tiene turnos pendientes o confirmados",
                exception.getMessage());
        verify(doctorRepository, never()).save(any());

    }

    @Test
    void darDeBaja_deberiaFallar_siElDoctorNoExiste() {
        when(doctorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            doctorService.darDeBaja(99L);
        });
    }

}
