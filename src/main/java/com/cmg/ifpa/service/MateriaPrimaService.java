package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.MateriaPrima;

public interface MateriaPrimaService {
    public Page<MateriaPrima> findAll(Pageable pageable);

    public MateriaPrima findById(Long id);

    public MateriaPrima save(MateriaPrima model);

    public void delete(Long id);
    Page<MateriaPrima> buscarPorNombre(String nombre, Pageable pageable);
}
