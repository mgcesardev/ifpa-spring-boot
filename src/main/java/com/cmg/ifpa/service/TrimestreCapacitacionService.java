package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.TrimestreCapacitacion;

public interface TrimestreCapacitacionService {
    public Page<TrimestreCapacitacion> findAll(Pageable pageable);

    public TrimestreCapacitacion findById(Long id);

    public TrimestreCapacitacion save(TrimestreCapacitacion model);

    public void delete(Long id);
    Page<TrimestreCapacitacion> buscarPorNombre(String nombre, Pageable pageable);
}
