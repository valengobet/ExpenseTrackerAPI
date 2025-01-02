package com.crud.GestionGastos.repositories;

import com.crud.GestionGastos.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FactGastosRepository extends CrudRepository<FactGastosModel, Long> {

    @Query("SELECT f FROM FactGastosModel f " +
            "JOIN FETCH f.categoria " +
            "JOIN FETCH f.metodoPago " +
            "JOIN FETCH f.destinatario")
    List<FactGastosModel> findAllWithJoins();

    @Query("SELECT f FROM FactGastosModel f " +
            "JOIN FETCH f.categoria " +
            "JOIN FETCH f.metodoPago " +
            "JOIN FETCH f.destinatario " +
            "WHERE f.id = :id")
    FactGastosModel findByIdWithJoins(@Param("id") Long id);


    @Query("SELECT f FROM FactGastosModel f " +
            "JOIN FETCH f.categoria " +
            "JOIN FETCH f.metodoPago " +
            "JOIN FETCH f.destinatario " +
            "WHERE f.categoria = :categoria")
    List<FactGastosModel> findByCategoria(@Param("categoria") CategoriaModel categoria);

    @Query("SELECT f FROM FactGastosModel f " +
            "JOIN FETCH f.categoria " +
            "JOIN FETCH f.metodoPago " +
            "JOIN FETCH f.destinatario " +
            "WHERE f.metodoPago = :metodoPago")
    List<FactGastosModel> findByMetodoPago(@Param("metodoPago") MetodoPagoModel metodoPago);

    @Query("SELECT f FROM FactGastosModel f " +
            "JOIN FETCH f.categoria " +
            "JOIN FETCH f.metodoPago " +
            "JOIN FETCH f.destinatario " +
            "WHERE f.destinatario = :destinatario")
    List<FactGastosModel> findByDestinatario(@Param("destinatario") DestinatarioModel destinatario);

    @Query("SELECT f FROM FactGastosModel f " +
            "JOIN FETCH f.categoria " +
            "JOIN FETCH f.metodoPago " +
            "JOIN FETCH f.destinatario " +
            "WHERE f.fecha = :fecha")
    List<FactGastosModel> findByFecha(@Param("fecha") LocalDate fecha);

    @Query("SELECT f FROM FactGastosModel f " +
            "JOIN FETCH f.categoria " +
            "JOIN FETCH f.metodoPago " +
            "JOIN FETCH f.destinatario " +
            "WHERE FUNCTION('MONTH', f.fecha) = :mes")
    List<FactGastosModel> findByMes(@Param("mes") int mes);

    @Query("SELECT f FROM FactGastosModel f " +
            "JOIN FETCH f.categoria " +
            "JOIN FETCH f.metodoPago " +
            "JOIN FETCH f.destinatario " +
            "WHERE FUNCTION('YEAR', f.fecha) = :anio")
    List<FactGastosModel> findByAnio(@Param("anio") int anio);

}