package com.crud.GestionGastos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

public class GastosModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Double monto;

    private LocalDate fecha;

    private String descripcion;

    private String categoria;

    private String metodo_pago;

    private String destinatario;

    public GastosModel(Long id, Double monto, LocalDate fecha, String descripcion, String categoria, String metodo_pago, String destinatario) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.metodo_pago = metodo_pago;
        this.destinatario = destinatario;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId(){ return id; }

    public void setId(Long id) { this.id = id; }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMetodopago() {
        return metodo_pago;
    }

    public void setMetodopago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
}