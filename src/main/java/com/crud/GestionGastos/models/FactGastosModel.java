package com.crud.GestionGastos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fact_gastos")
public class FactGastosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaModel categoria;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    private MetodoPagoModel metodoPago;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private DestinatarioModel destinatario;

    public FactGastosModel() {
    }

    public FactGastosModel(Double monto, LocalDate fecha, String descripcion, CategoriaModel categoria, MetodoPagoModel metodoPago, DestinatarioModel destinatario) {
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.metodoPago = metodoPago;
        this.destinatario = destinatario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public MetodoPagoModel getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPagoModel metodoPago) {
        this.metodoPago = metodoPago;
    }

    public DestinatarioModel getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(DestinatarioModel destinatario) {
        this.destinatario = destinatario;
    }
}

