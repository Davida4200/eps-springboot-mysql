package com.citas.apicitas.services;

import java.util.Set;

import com.citas.apicitas.entities.Doctor;

public interface DoctorService {
  Set<Doctor> findAll();
  Doctor findById(Long id);
  Set<Doctor> findAllByEspecialidad(Doctor.Especialidad especialidad);

  Doctor addDoctor(Doctor doctor);
  Doctor modifyDoctor(Long id, Doctor doctor);
  void deleteDoctor(Long id);
}
