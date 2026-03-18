package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.ProgramaCapacitacion;

public interface ProgramaCapacitacionService {
    public Page<ProgramaCapacitacion> findAll(Pageable pageable);

    public ProgramaCapacitacion findById(Long id);

    public ProgramaCapacitacion save(ProgramaCapacitacion model);

    public void delete(Long id);
    Page<ProgramaCapacitacion> buscarPorNombre(String nombre, Pageable pageable);
}
