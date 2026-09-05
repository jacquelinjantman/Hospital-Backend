package com.hospital.backend.service;

import com.hospital.backend.model.Doctor;
import com.hospital.backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> listarTodos() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> buscarPorId(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor guardar(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void eliminar(Long id) {
        doctorRepository.deleteById(id);
    }
}
