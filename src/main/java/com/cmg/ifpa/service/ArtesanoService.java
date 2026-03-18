package com.cmg.ifpa.service;

import java.io.ByteArrayInputStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.Artesano;

public interface ArtesanoService {

    public Page<Artesano> findAll(String estatus, Pageable pageable);

    public Page<Artesano> buscarPorNombre(String nombre, String estatus, Pageable pageable);

    public Artesano findById(Long id);

    public Artesano save(Artesano artesano);

    public void delete(Long id);

    public ByteArrayInputStream exportToExcel(String estatus);

}
