package com.citas.apicitas.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.CitaId;

public interface CitaRepository  extends CrudRepository<Cita, CitaId>{
  Set<Cita> findAll();
  Optional<Cita> findById(CitaId id);
}
