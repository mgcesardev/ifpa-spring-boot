package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.MateriaPrima;

public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Long> {

    @Query("SELECT e FROM MateriaPrima e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<MateriaPrima> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
