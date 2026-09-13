package com.hospital.backend.service;

import com.hospital.backend.model.Turno;
import com.hospital.backend.model.DisponibilidadDoctor;
import com.hospital.backend.repository.DisponibilidadDoctorRepository;
import com.hospital.backend.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired 
    private DisponibilidadDoctorRepository disponibilidadDoctorRepository;

    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarPorId(Long id) {
        return turnoRepository.findById(id);
    }

    public Turno guardar(Turno turno) {
       var diaSemana = turno.getFechaHora().getDayOfWeek();
        var horaTurno = turno.getFechaHora().toLocalTime();

   List<DisponibilidadDoctor> bloques = disponibilidadDoctorRepository
        .findByDoctorIdAndDiaSemana(turno.getDoctor().getId(), diaSemana);

        boolean dentroDeHorario = bloques.stream().anyMatch(b ->
                !horaTurno.isBefore(b.getHoraInicio()) && !horaTurno.isAfter(b.getHoraFin())
        );

        if (!dentroDeHorario) {
            throw new RuntimeException("El doctor no atiende en ese día/horario");
        }

        boolean turnoOcupado = turnoRepository.existsByDoctorIdAndFechaHora(
                turno.getDoctor().getId(), turno.getFechaHora());

        if (turnoOcupado) {
            throw new RuntimeException("Ya existe un turno para ese doctor en ese horario exacto");
        }

        return turnoRepository.save(turno);
    }
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }
}

