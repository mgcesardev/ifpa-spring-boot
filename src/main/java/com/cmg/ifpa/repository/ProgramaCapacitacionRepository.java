package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.ProgramaCapacitacion;

public interface ProgramaCapacitacionRepository extends JpaRepository<ProgramaCapacitacion, Long> {

    @Query("SELECT e FROM ProgramaCapacitacion e WHERE LOWER(e.nombrePrograma) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<ProgramaCapacitacion> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
