package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT e FROM Evento e WHERE LOWER(e.folio) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Evento> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
