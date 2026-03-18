package com.cmg.ifpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;
import com.cmg.ifpa.model.Region;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query(value = "SELECT * FROM region WHERE estatus = :estatus", nativeQuery = true)
    List<Region> findByEstatusNative(@Param("estatus") String estatus);

    @Query("SELECT e FROM Region e WHERE LOWER(e.region) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Region> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
