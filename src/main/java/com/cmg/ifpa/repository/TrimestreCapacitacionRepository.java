package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.TrimestreCapacitacion;

public interface TrimestreCapacitacionRepository extends JpaRepository<TrimestreCapacitacion, Long> {

    @Query("SELECT e FROM TrimestreCapacitacion e WHERE LOWER(e.meses) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<TrimestreCapacitacion> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
