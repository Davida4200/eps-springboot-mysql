package com.citas.apicitas.controllers;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.CitaId;
import com.citas.apicitas.entities.Doctor;
import com.citas.apicitas.entities.Paciente;
import com.citas.apicitas.services.CitaService;
import com.citas.apicitas.services.DoctorService;
import com.citas.apicitas.services.PacienteService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

  @Autowired
  private CitaService citaService;

  @Autowired
  private DoctorService doctorService;

  @Autowired
  private PacienteService pacienteService;

    @GetMapping("")
  public ResponseEntity <Set<Cita>> getCitas(){
    Set<Cita> citas = citaService.findAll();

    return new ResponseEntity<>(citas, HttpStatus.OK);
  }

  //Uso de parametros query en este endpoint
  @GetMapping("/one-cita")
  public ResponseEntity <Cita> getCita(@RequestParam Long idPaciente,
  @RequestParam Long idDoctor, @RequestParam LocalDateTime fecha_hora){

    CitaId citaId = new CitaId(idDoctor, idPaciente, fecha_hora);

    Cita cita = citaService.findById(citaId);

    return new ResponseEntity<>(cita, HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity <CitaId> addCita(@RequestBody CitaId citaId){
    Doctor doctor = doctorService.findById(citaId.getIdProfesional());
    Paciente paciente = pacienteService.findById(citaId.getIdNumeroCedula());

    Cita newCita = new Cita();

    newCita.setId(citaId);
    newCita.setDoctor(doctor);
    newCita.setPaciente(paciente);

    Cita addedCita = citaService.addCita(citaId, newCita);

    return new ResponseEntity<>(addedCita.getId(), HttpStatus.CREATED);
  }

  @PutMapping("")
  public ResponseEntity <Cita> modifyCita(@RequestBody CitaId citaId, @RequestParam Long idPaciente,
  @RequestParam Long idDoctor, @RequestParam LocalDateTime fecha_hora){

    CitaId oldCita = new CitaId(idDoctor, idPaciente, fecha_hora);

    Cita modifyCita = citaService.modifyCita(oldCita, citaId);

    return new ResponseEntity<>(modifyCita, HttpStatus.OK);
  }

  @DeleteMapping("")
  public ResponseEntity <Void> deleteCita(@RequestBody CitaId id){
    citaService.deleteCita(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
