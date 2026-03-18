package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.InscripcionCapacitacion;

public interface InscripcionCapacitacionRepository extends JpaRepository<InscripcionCapacitacion, Long> {

    @Query("SELECT e FROM InscripcionCapacitacion e WHERE LOWER(e.artesano.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<InscripcionCapacitacion> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
