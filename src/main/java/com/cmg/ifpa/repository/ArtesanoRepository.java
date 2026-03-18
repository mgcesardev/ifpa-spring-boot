package com.cmg.ifpa.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmg.ifpa.model.Artesano;
import com.cmg.ifpa.model.Region;

public interface ArtesanoRepository extends JpaRepository<Artesano, Long> {

    @Query(value = "SELECT * FROM region WHERE estatus = :estatus", nativeQuery = true)
    List<Region> findByEstatusNative(@Param("estatus") String estatus);

    @Query("SELECT a FROM Artesano a WHERE " +
           "LOWER(CONCAT(COALESCE(a.nombre, ''), ' ', COALESCE(a.primerApellido, ''), ' ', COALESCE(a.segundoApellido, ''))) " +
           "LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Artesano> buscarPorNombreCompleto(@Param("nombre") String nombre, Pageable pageable);
    
    @Query("SELECT a FROM Artesano a WHERE " +
           "LOWER(CONCAT(COALESCE(a.nombre, ''), ' ', COALESCE(a.primerApellido, ''), ' ', COALESCE(a.segundoApellido, ''))) " +
           "LIKE LOWER(CONCAT('%', :nombre, '%')) AND a.estatus = :estatus")
    Page<Artesano> buscarPorNombreCompletoAndEstatus(@Param("nombre") String nombre, @Param("estatus") String estatus, Pageable pageable);

    List<Artesano> findByEstatus(String estatus);
    Page<Artesano> findByEstatus(String estatus, Pageable pageable);
}
