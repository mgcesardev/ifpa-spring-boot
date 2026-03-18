package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.InscripcionCapacitacion;

public interface InscripcionCapacitacionService {
    public Page<InscripcionCapacitacion> findAll(Pageable pageable);

    public InscripcionCapacitacion findById(Long id);

    public InscripcionCapacitacion save(InscripcionCapacitacion model);

    public void delete(Long id);
    Page<InscripcionCapacitacion> buscarPorNombre(String nombre, Pageable pageable);
}
