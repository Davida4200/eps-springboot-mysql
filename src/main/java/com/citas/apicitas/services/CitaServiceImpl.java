package com.citas.apicitas.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.CitaId;
import com.citas.apicitas.entities.Doctor;
import com.citas.apicitas.entities.Paciente;
import com.citas.apicitas.exception.ResourceNotFoundException;
import com.citas.apicitas.repositories.CitaRepository;

@Service
public class CitaServiceImpl implements CitaService{

  @Autowired
  private CitaRepository citaRepository;

  @Autowired
  private DoctorService doctorService;

  @Autowired
  private PacienteService pacienteService;

  @Override
  public Set<Cita> findAll() {
    return citaRepository.findAll();
  }

  @Override
  public Cita findById(CitaId id) {
    Cita cita = citaRepository.findById(id)
    .orElseThrow(( ) -> new ResourceNotFoundException("Cita not found with id: " + id));

    return cita;
  }

  @Override
  public Cita addCita(CitaId id, Cita cita) {
    Optional <Cita> citaExist = citaRepository.findById(id);

    if(citaExist.isPresent()){
      throw new DataIntegrityViolationException("Primary key already exists");
    } else {
      return citaRepository.save(cita);
    }

    //Segunda manera de hacer la validación
    // if(citaRepository.existsById(id)){
    //   throw new DataIntegrityViolationException("Primary key already exists");
    // }

    // return citaRepository.save(cita);
  }

  @Override
  public Cita modifyCita(CitaId id, CitaId newCita) {
    Cita cita = citaRepository.findById(id)
    .orElseThrow(( ) -> new ResourceNotFoundException("Cita not found with id: " + id));

    Doctor doctor = doctorService.findById(newCita.getIdProfesional());
    Paciente paciente = pacienteService.findById(newCita.getIdNumeroCedula());
    Cita nuevaCita = new Cita();

    nuevaCita.setId(newCita);

    nuevaCita.setDoctor(doctor);
    nuevaCita.setPaciente(paciente);
    // cita.setId(newCita);

    citaRepository.delete(cita);

    return citaRepository.save(nuevaCita);
  }

  @Override
  public void deleteCita(CitaId id) {
    citaRepository.findById(id)
    .orElseThrow(( ) -> new ResourceNotFoundException("Cita not found with id: " + id));

    citaRepository.deleteById(id);
  }
}

