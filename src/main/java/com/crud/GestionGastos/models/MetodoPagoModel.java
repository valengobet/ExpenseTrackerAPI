package com.crud.GestionGastos.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "metodo_pago")
public class MetodoPagoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Permite que al crear un nuevo registro no se muestre en el JSON y solo
    // se muestre en la lectura.
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre no puede estar vacio.")
    private String nombre;

    public MetodoPagoModel() {}

    public MetodoPagoModel(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
