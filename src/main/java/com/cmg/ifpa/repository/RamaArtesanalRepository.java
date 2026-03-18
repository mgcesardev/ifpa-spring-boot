package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.RamaArtesanal;

public interface RamaArtesanalRepository extends JpaRepository<RamaArtesanal, Long> {

    @Query("SELECT e FROM RamaArtesanal e WHERE LOWER(e.nombreRama) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<RamaArtesanal> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
