package com.cmg.ifpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.LenguaIndigena;

public interface LenguaIndigenaRepository extends JpaRepository<LenguaIndigena, Long> {

    @Query("SELECT e FROM LenguaIndigena e WHERE LOWER(e.lenguaIndigena) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<LenguaIndigena> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
