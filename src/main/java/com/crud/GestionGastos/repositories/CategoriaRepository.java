package com.crud.GestionGastos.repositories;

import com.crud.GestionGastos.models.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
    CategoriaModel findByNombre(String nombre);
}
