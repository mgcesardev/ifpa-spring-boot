package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.GrupoEtnico;

public interface GrupoEtnicoRepository extends JpaRepository<GrupoEtnico, Long> {

    @Query("SELECT e FROM GrupoEtnico e WHERE LOWER(e.nombreEtnia) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<GrupoEtnico> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
