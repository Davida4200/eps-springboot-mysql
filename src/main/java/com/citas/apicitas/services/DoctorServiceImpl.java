package com.citas.apicitas.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.citas.apicitas.entities.Doctor;
import com.citas.apicitas.exception.ResourceNotFoundException;
import com.citas.apicitas.repositories.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{

  @Autowired
  private DoctorRepository doctorRepository;

  @Override
  public Set<Doctor> findAll() {
    return doctorRepository.findAll();
  }

  @Override
  public Doctor findById(Long id) {
    Doctor doctor = doctorRepository.findById(id)
    .orElseThrow(( ) -> new ResourceNotFoundException("Doctor not found with id: " + id));

    return doctor;
  }

  @Override
  public Doctor addDoctor(Doctor doctor) {
    Optional <Doctor> doctorExist = doctorRepository.findById(doctor.getIdProfesional());

    if(doctorExist.isPresent()){
      throw new DataIntegrityViolationException("Primary key already exists");
    } else {
      return doctorRepository.save(doctor);
    }
  }

  @Override
  public Doctor modifyDoctor(Long id, Doctor newDoctor) {
    Doctor doctor = doctorRepository.findById(id)
    .orElseThrow(( ) -> new ResourceNotFoundException("Doctor not found with id: " + id));

    newDoctor.setIdProfesional(doctor.getIdProfesional());

    return doctorRepository.save(newDoctor);
  }

  @Override
  public void deleteDoctor(Long id) {
    doctorRepository.findById(id)
    .orElseThrow(( ) -> new ResourceNotFoundException("Doctor not found with id: " + id));

    doctorRepository.deleteById(id);
  }

  @Override
  public Set<Doctor> findAllByEspecialidad(Doctor.Especialidad especialidad){
    return doctorRepository.findAllByEspecialidad(especialidad);
  }
}
