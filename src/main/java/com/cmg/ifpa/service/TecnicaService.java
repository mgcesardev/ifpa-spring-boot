package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.Tecnica;

public interface TecnicaService {
    public Page<Tecnica> findAll(Pageable pageable);

    public Tecnica findById(Long id);

    public Tecnica save(Tecnica model);

    public void delete(Long id);
    Page<Tecnica> buscarPorNombre(String nombre, Pageable pageable);
}
