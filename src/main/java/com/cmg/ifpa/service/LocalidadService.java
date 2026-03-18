package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.Localidad;

public interface LocalidadService {
    public Page<Localidad> findAll(Pageable pageable);

    public Localidad findById(Long id);

    public Localidad save(Localidad model);

    public void delete(Long id);
    Page<Localidad> buscarPorNombre(String nombre, Pageable pageable);
}
