package com.crud.GestionGastos.repositories;

import com.crud.GestionGastos.models.MetodoPagoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPagoModel, Long> {
    MetodoPagoModel findByNombre(String nombre);
}
