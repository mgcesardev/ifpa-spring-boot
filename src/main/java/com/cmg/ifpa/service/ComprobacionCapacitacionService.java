package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.ComprobacionCapacitacion;

public interface ComprobacionCapacitacionService {
    public Page<ComprobacionCapacitacion> findAll(Pageable pageable);

    public ComprobacionCapacitacion findById(Long id);

    public ComprobacionCapacitacion save(ComprobacionCapacitacion model);

    public void delete(Long id);
    Page<ComprobacionCapacitacion> buscarPorNombre(String nombre, Pageable pageable);
}
