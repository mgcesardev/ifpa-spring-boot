package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.LenguaIndigena;

public interface LenguaIndigenaService {
    public Page<LenguaIndigena> findAll(Pageable pageable);

    public LenguaIndigena findById(Long id);

    public LenguaIndigena save(LenguaIndigena model);

    public void delete(Long id);
    Page<LenguaIndigena> buscarPorNombre(String nombre, Pageable pageable);
}
