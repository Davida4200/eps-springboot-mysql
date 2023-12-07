package com.citas.apicitas.services;

import java.util.Set;

import com.citas.apicitas.entities.Paciente;

public interface PacienteService {
  Set<Paciente> findAll();
  Paciente findById(Long id);

  Paciente addPaciente(Paciente paciente);
  Paciente modifyPaciente(Long id, Paciente paciente);
  void deletePaciente(Long id);
}
