package com.citas.apicitas.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cita")
public class Cita {
  @EmbeddedId
  private CitaId id;

  @MapsId("idProfesional")
  @ManyToOne
  @JoinColumn(name = "id_profesional")
  private Doctor doctor;

  @MapsId("idNumeroCedula")
  @ManyToOne
  @JoinColumn(name = "id_numero_cedula")
  private Paciente paciente;
}
