package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.Tecnica;

public interface TecnicaRepository extends JpaRepository<Tecnica, Long> {

    @Query("SELECT e FROM Tecnica e WHERE LOWER(e.nombreTecnica) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Tecnica> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
