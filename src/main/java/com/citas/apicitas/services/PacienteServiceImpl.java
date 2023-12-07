package com.citas.apicitas.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.citas.apicitas.entities.Paciente;
import com.citas.apicitas.exception.ResourceNotFoundException;
import com.citas.apicitas.repositories.PacienteRepository;

@Service
public class PacienteServiceImpl implements PacienteService{

  @Autowired
  private PacienteRepository pacienteRepository;

  @Override
  public Set<Paciente> findAll() {
    return pacienteRepository.findAll();
  }

  @Override
  public Paciente findById(Long id) {
    Paciente paciente = pacienteRepository.findById(id)
    .orElseThrow(( ) -> new ResourceNotFoundException("Paciente not found with id: " + id));

    return paciente;
  }

  @Override
  public Paciente addPaciente(Paciente paciente) {
    Optional <Paciente> pacienteExist = pacienteRepository.findById(paciente.getIdNumeroCedula());

    if(pacienteExist.isPresent()){
      throw new DataIntegrityViolationException("Primary key already exists");
    } else {
      return pacienteRepository.save(paciente);
    }
  }

  @Override
  public Paciente modifyPaciente(Long id, Paciente newPaciente) {
    Paciente paciente = pacienteRepository.findById(id)
    .orElseThrow(( ) -> new ResourceNotFoundException("Paciente not found with id: " + id));

    newPaciente.setIdNumeroCedula(paciente.getIdNumeroCedula());

    return pacienteRepository.save(newPaciente);
  }

  @Override
  public void deletePaciente(Long id) {
    pacienteRepository.findById(id)
    .orElseThrow(( ) -> new ResourceNotFoundException("Paciente not found with id: " + id));

    pacienteRepository.deleteById(id);
  }
}
