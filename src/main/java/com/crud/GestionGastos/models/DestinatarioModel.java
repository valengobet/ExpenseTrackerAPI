package com.crud.GestionGastos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "destinatario")
public class DestinatarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    public DestinatarioModel() {}

    public DestinatarioModel(String nombre) {
        this.nombre = nombre;
    }
    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getNombre(){return nombre;}

    public void setNombre(String nombre){this.nombre = nombre;}


}
