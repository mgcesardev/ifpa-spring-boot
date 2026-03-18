package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.AccionCapacitacion;

public interface AccionCapacitacionRepository extends JpaRepository<AccionCapacitacion, Long> {

    @Query("SELECT e FROM AccionCapacitacion e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<AccionCapacitacion> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
