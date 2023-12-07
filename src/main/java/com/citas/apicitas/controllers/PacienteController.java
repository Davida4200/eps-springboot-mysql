package com.citas.apicitas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.citas.apicitas.entities.Paciente;
import com.citas.apicitas.services.PacienteService;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

  @Autowired
  private PacienteService pacienteService;

  @GetMapping("")
  public ResponseEntity <Set<Paciente>> getPacientes(){
    Set<Paciente> pacientes = pacienteService.findAll();

    return new ResponseEntity<>(pacientes, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity <Paciente> getPaciente(@PathVariable Long id){
    Paciente paciente = pacienteService.findById(id);

    return new ResponseEntity<>(paciente, HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity <Paciente> addPaciente(@RequestBody Paciente paciente){
    Paciente newPaciente = pacienteService.addPaciente(paciente);

    return new ResponseEntity<>(newPaciente, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity <Paciente> modifyPaciente(@PathVariable Long id, @RequestBody Paciente paciente){
    Paciente modifyPaciente = pacienteService.modifyPaciente(id, paciente);

    return new ResponseEntity<>(modifyPaciente, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity <Void> deletePaciente(@PathVariable Long id){
    pacienteService.deletePaciente(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
