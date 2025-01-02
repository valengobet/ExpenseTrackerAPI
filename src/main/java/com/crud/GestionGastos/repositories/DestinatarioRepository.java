package com.crud.GestionGastos.repositories;

import com.crud.GestionGastos.models.DestinatarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinatarioRepository extends JpaRepository<DestinatarioModel, Long> {
    DestinatarioModel findByNombre(String nombre);
}
