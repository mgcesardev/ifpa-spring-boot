package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.GrupoEtnico;

public interface GrupoEtnicoService {
    public Page<GrupoEtnico> findAll(Pageable pageable);

    public GrupoEtnico findById(Long id);

    public GrupoEtnico save(GrupoEtnico model);

    public void delete(Long id);
    Page<GrupoEtnico> buscarPorNombre(String nombre, Pageable pageable);
}
