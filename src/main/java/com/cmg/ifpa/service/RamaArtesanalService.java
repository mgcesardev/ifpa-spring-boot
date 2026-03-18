package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.RamaArtesanal;

public interface RamaArtesanalService {
    public Page<RamaArtesanal> findAll(Pageable pageable);

    public RamaArtesanal findById(Long id);

    public RamaArtesanal save(RamaArtesanal model);

    public void delete(Long id);
    Page<RamaArtesanal> buscarPorNombre(String nombre, Pageable pageable);
}
