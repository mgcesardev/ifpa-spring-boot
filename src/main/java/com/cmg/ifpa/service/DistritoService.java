package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.Distrito;

public interface DistritoService {
    public Page<Distrito> findAll(Pageable pageable);

    public Distrito findById(Long id);

    public Distrito save(Distrito model);

    public void delete(Long id);
    Page<Distrito> buscarPorNombre(String nombre, Pageable pageable);
}
