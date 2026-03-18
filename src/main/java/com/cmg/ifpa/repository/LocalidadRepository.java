package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.Localidad;

public interface LocalidadRepository extends JpaRepository<Localidad, Long> {

    @Query("SELECT e FROM Localidad e WHERE LOWER(e.localidad) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Localidad> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
