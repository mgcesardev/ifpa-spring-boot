package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.Municipio;

public interface MunicipioService {
    public Page<Municipio> findAll(Pageable pageable);

    public Municipio findById(Long id);

    public Municipio save(Municipio model);

    public void delete(Long id);
    Page<Municipio> buscarPorNombre(String nombre, Pageable pageable);
}
