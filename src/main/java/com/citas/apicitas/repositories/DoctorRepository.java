package com.citas.apicitas.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.citas.apicitas.entities.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
  Set<Doctor> findAll();
  Set<Doctor> findAllByEspecialidad(Doctor.Especialidad especialidad);

}
