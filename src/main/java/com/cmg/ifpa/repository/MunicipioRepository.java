package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    @Query("SELECT e FROM Municipio e WHERE LOWER(e.municipio) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Municipio> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
