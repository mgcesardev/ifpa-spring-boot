package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.Evento;

public interface EventoService {
    public Page<Evento> findAll(Pageable pageable);

    public Evento findById(Long id);

    public Evento save(Evento model);

    public void delete(Long id);
    Page<Evento> buscarPorNombre(String nombre, Pageable pageable);
}
