package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.Organizacion;

public interface OrganizacionService {
    public Page<Organizacion> findAll(Pageable pageable);

    public Organizacion findById(Long id);

    public Organizacion save(Organizacion model);

    public void delete(Long id);
    Page<Organizacion> buscarPorNombre(String nombre, Pageable pageable);
}
