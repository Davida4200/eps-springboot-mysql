package com.citas.apicitas.services;

import java.util.Set;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.CitaId;

public interface CitaService {
  Set<Cita> findAll();
  Cita findById(CitaId id);
  Cita addCita(CitaId id, Cita cita);
  Cita modifyCita(CitaId id, CitaId newCita);
  void deleteCita(CitaId id);

}
