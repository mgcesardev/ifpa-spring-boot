package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.TipoComprador;

public interface TipoCompradorService {
    public Page<TipoComprador> findAll(Pageable pageable);

    public TipoComprador findById(Long id);

    public TipoComprador save(TipoComprador model);

    public void delete(Long id);
    Page<TipoComprador> buscarPorNombre(String nombre, Pageable pageable);
}
