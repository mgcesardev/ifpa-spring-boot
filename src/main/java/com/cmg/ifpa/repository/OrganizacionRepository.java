package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.Organizacion;

public interface OrganizacionRepository extends JpaRepository<Organizacion, Long> {

    @Query("SELECT e FROM Organizacion e WHERE LOWER(e.nombreOrganizacion) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Organizacion> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
