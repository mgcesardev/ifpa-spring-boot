package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.Distrito;

public interface DistritoRepository extends JpaRepository<Distrito, Long> {

    @Query("SELECT e FROM Distrito e WHERE LOWER(e.distrito) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Distrito> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
