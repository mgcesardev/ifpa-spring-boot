package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.ComprobacionCapacitacion;

public interface ComprobacionCapacitacionRepository extends JpaRepository<ComprobacionCapacitacion, Long> {

    @Query("SELECT e FROM ComprobacionCapacitacion e WHERE LOWER(e.artesano.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<ComprobacionCapacitacion> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
