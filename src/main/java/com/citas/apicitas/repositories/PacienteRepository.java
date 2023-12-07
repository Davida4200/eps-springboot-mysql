package com.citas.apicitas.repositories;

import org.springframework.data.repository.CrudRepository;
import java.util.Set;

import com.citas.apicitas.entities.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente , Long> {
  Set<Paciente> findAll();
}
