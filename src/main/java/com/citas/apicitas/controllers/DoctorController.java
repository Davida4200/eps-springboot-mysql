package com.citas.apicitas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.citas.apicitas.entities.Doctor;
import com.citas.apicitas.services.DoctorService;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

  @Autowired
  private DoctorService doctorService;

  @GetMapping("")
  public ResponseEntity <Set<Doctor>> getDoctors(){
    Set<Doctor> doctors = doctorService.findAll();

    return new ResponseEntity<>(doctors, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity <Doctor> getDoctor(@PathVariable Long id){
    Doctor doctor = doctorService.findById(id);

    return new ResponseEntity<>(doctor, HttpStatus.OK);
  }

  @GetMapping("/especialidad/{especialidad}")
  public ResponseEntity <Set<Doctor>> getDoctorsByEspecialidad(@PathVariable Doctor.Especialidad especialidad){
    Set<Doctor> doctor = doctorService.findAllByEspecialidad(especialidad);

    return new ResponseEntity<>(doctor, HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity <Doctor> addDoctor(@RequestBody Doctor doctor){
    Doctor newDoctor = doctorService.addDoctor(doctor);

    return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity <Doctor> modifyDoctor(@PathVariable Long id, @RequestBody Doctor doctor){
    Doctor modifyDoctor = doctorService.modifyDoctor(id, doctor);

    return new ResponseEntity<>(modifyDoctor, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity <Void> deleteDoctor(@PathVariable Long id){
    doctorService.deleteDoctor(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

