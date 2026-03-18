package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.AccionCapacitacion;

public interface AccionCapacitacionService {
    public Page<AccionCapacitacion> findAll(Pageable pageable);

    public AccionCapacitacion findById(Long id);

    public AccionCapacitacion save(AccionCapacitacion model);

    public void delete(Long id);
    Page<AccionCapacitacion> buscarPorNombre(String nombre, Pageable pageable);
}
