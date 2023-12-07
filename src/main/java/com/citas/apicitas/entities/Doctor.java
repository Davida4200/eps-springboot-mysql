package com.citas.apicitas.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "doctor")
public class Doctor {

  @Id
  @Column(name = "id_profesional")
  private long idProfesional;

  @Column
  private String nombre;

  @Column
  private String apellido;

  @Column
  private String correo;

  @Enumerated(EnumType.STRING)
  @Column
  private Especialidad especialidad;

  @JsonIgnore
  @OneToMany(mappedBy = "doctor")
  private Set<Cita> citas = new HashSet<>();

  public enum Especialidad{medicina_interna, medicina_general};

}
